package com.wjf.dev.main.fragment.navigation

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.NavigationFragmentBinding
import com.wjf.dev.main.fragment.navigation.adapter.NavAdapter
import kotlinx.android.synthetic.main.navigation_fragment.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView
import q.rorbin.verticaltablayout.widget.TabView

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:37
 *
 */


class NavigationFragment : BaseMVVMFragment<NavigationFragmentBinding,NavigationViewModel>() {

    val navAdapter = NavAdapter()
    private var onScrollListener: RecyclerView.OnScrollListener? = null
    //用于recyclerView滑动到指定的位置
    private var canScroll: Boolean = false
    //VerticalTabLayout 点击获取位置让 RecycleView滑动到相应位置
    private var scrollToPosition: Int = 0
    private var linearLayoutManager: LinearLayoutManager? = null
    //是否点击了 Tab
    private var isClickTab: Boolean = false
    //指向位置
    private var indexPosition: Int = 0
    private var tabSelectedListener: VerticalTabLayout.OnTabSelectedListener? = null
    override fun initViewModel(): NavigationViewModel =
        ViewModelProvider(this,NavigationVMFactory(NavigationRepository())).get(NavigationViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.navigation_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initData()
        initRequest()
    }

    fun initData(){


        nav_recycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        nav_recycler.layoutManager = linearLayoutManager

        nav_recycler.adapter = navAdapter

        vm.dataList.observe(viewLifecycleOwner, Observer {
            navAdapter.replaceData(it)
            VerticalTabLayoutWithRecyclerView()
            nav_tab.setTabAdapter(object : TabAdapter{
                override fun getIcon(position: Int): ITabView.TabIcon? {

                    return null
                }

                override fun getBadge(position: Int): ITabView.TabBadge? {

                    return null
                }

                override fun getBackground(position: Int): Int {

                    return -1
                }

                override fun getTitle(position: Int): ITabView.TabTitle {
                    return ITabView.TabTitle.Builder()
                        .setContent(it[position].name)
                        .setTextColor(
                            ContextCompat.getColor(context!!, R.color.textColorPress),
                            ContextCompat.getColor(context!!, R.color.textColorPrimary)
                        )
                        .build()                }

                override fun getCount(): Int {
                    return it.size
                }

            })
        })


    }

    fun initRequest(){

        vm.getNavigationData()
    }

    fun VerticalTabLayoutWithRecyclerView(){

        onScrollListener = object : RecyclerView.OnScrollListener() {
            // RecyclerView 滚动状态变化时回调
            override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (canScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    canScroll = false
                    //moveToPosition(layoutManager, recyclerView, scrollToPosition);
                    RecyclerViewSmoothScroll()
                }
                //RecyclerView 滑动关联 VerticalTabLayout
                RecyclerViewWithTabLayout(newState)
            }

            // RecyclerView 滚动时回调
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (canScroll) {
                    canScroll = false
                    RecyclerViewSmoothScroll()
                }
            }
        }

        tabSelectedListener = object : VerticalTabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabView, position: Int) {
                isClickTab = false
                //点击标签，使recyclerView滑动
                moveToPosition(linearLayoutManager!!, nav_recycler, position)
            }

            override fun onTabReselected(tab: TabView, position: Int) {

            }
        }

        nav_recycler.addOnScrollListener(onScrollListener as RecyclerView.OnScrollListener)
        nav_tab.addOnTabSelectedListener(tabSelectedListener)

    }

    //滑动到对应位置
    private fun RecyclerViewSmoothScroll() {
        val indexPositionDistance = scrollToPosition - linearLayoutManager!!.findFirstVisibleItemPosition()
        if (indexPositionDistance >= 0 && indexPositionDistance < nav_recycler.childCount) {
            val top = nav_recycler.getChildAt(indexPositionDistance).top
            nav_recycler.smoothScrollBy(0, top)
        }
    }


    private fun RecyclerViewWithTabLayout(newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                //点击tab 不执行以下操作
                isClickTab = false
                return
            }
            val firstPosition = linearLayoutManager!!.findFirstVisibleItemPosition()
            if (indexPosition != firstPosition) {
                indexPosition = firstPosition
                //使 TabLayout 选择正确的item
                setTabLayoutChecked(indexPosition)
            }
        }
    }
    private fun setTabLayoutChecked(position: Int) {
        if (isClickTab) {
            isClickTab = false
        } else {
            if (nav_tab == null) {
                return
            }
            nav_tab.setTabSelected(indexPosition)
        }
        indexPosition = position
    }

    //VerticalTabLayout 点击使 RecyclerView 滑动到对应位置
    private fun moveToPosition(layoutManager: LinearLayoutManager, recyclerView: RecyclerView, position: Int) {
        // 第一个可见的view的位置
        var firstItem = layoutManager.findFirstVisibleItemPosition()
        // 最后一个可见的view的位置
        var lastItem = layoutManager.findLastVisibleItemPosition()
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            recyclerView.smoothScrollToPosition(position)
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            val top = recyclerView.getChildAt(position - firstItem).top
            recyclerView.smoothScrollBy(0, top)
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            recyclerView.smoothScrollToPosition(position)
            scrollToPosition = position
            canScroll = true
        }
    }

}