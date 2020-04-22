package com.wjf.dev.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wjf.dev.R
import com.wjf.dev.base.BaseActivity
import com.wjf.dev.main.fragment.home.fragment.HomeFragment
import com.wjf.dev.main.fragment.knowledge.KnowledgeFragment
import com.wjf.dev.main.fragment.navigation.NavigationFragment
import com.wjf.dev.main.fragment.offcialAccount.OfficialAccountFragment
import com.wjf.dev.main.fragment.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initContentViewID(): Int = R.layout.activity_main

    var mainFragmentManager : MainFragmentManager? = null

    override fun onViewCreated() {
        super.onViewCreated()
        //初始化Fragment管理类
        mainFragmentManager = MainFragmentManager(supportFragmentManager, home_container.id)

        //设置底部导航选择监听
        home_nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        home_nav_view.selectedItemId = R.id.nav_home
    }

    private fun setTitleText(title : String){
        main_toolbar.setBackground(R.color.colorPrimary)
        main_toolbar.setTitle(title)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.nav_home -> {
                setTitleText("首页")
                mainFragmentManager!!.select(0)
                return@OnNavigationItemSelectedListener true
            }


            R.id.nav_knowledge -> {
                setTitleText("体系")
                mainFragmentManager!!.select(1)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_official_account -> {
                setTitleText("公众号")
                mainFragmentManager!!.select(2)
                return@OnNavigationItemSelectedListener true

            }

            R.id.nav_navigation -> {
                setTitleText("导航")
                mainFragmentManager!!.select(3)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_mine -> {
                setTitleText("我的")
                mainFragmentManager!!.select(4)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun fitTransparentStatus() {
        super.fitTransparentStatus()
        main_toolbar.fitTransparentStatus()
    }

    class MainFragmentManager(val fragmentManager: FragmentManager,val containerId : Int){


        var lastFragment = 0
        var fragments = mutableListOf<Fragment>()

        init {

            fragments.add(HomeFragment())
            fragments.add(KnowledgeFragment())
            fragments.add(OfficialAccountFragment())
            fragments.add(NavigationFragment())
            fragments.add(MineFragment())
            fragmentManager.beginTransaction().replace(containerId,fragments[0]).commit()
        }

        fun select(position: Int) {
            val transaction = fragmentManager.beginTransaction()
            if (lastFragment != position) {
                //隐藏上一个fragment
                transaction.hide(fragments[lastFragment])
                //如果这个fragment没有添加到Transaction中，那么进行添加
                if (!fragments[position].isAdded) {
                    transaction.add(containerId, fragments[position])
                }
                transaction.show(fragments[position]).commitAllowingStateLoss()
                lastFragment = position
            }
        }

    }
}
