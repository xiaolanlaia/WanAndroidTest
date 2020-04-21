package com.wjf.dev.main.fragment.mine

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.common.cookie.CookieManager
import com.wjf.dev.entity.CollectBean
import com.wjf.dev.entity.IntegralListBean
import com.wjf.dev.entity.IntegralRankBean
import com.wjf.dev.main.fragment.home.HomeViewModel.Companion.setCollectState
import com.wjf.dev.util.CodeUtil.checkIsLogin
import com.wjf.dev.util.SharedHelper
import com.wjf.dev.util.addTo
import com.wjf.dev.util.toast
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class MineViewModel(val repository: MineRepository) : ViewModel() {

    val co = CompositeDisposable()

    var account = MutableLiveData<String>("点击登录")
    var level = MutableLiveData<String>()
    var mineIntegral = MutableLiveData<String>()

    val collectList = MutableLiveData<List<CollectBean.dataBean.datasBean>>()
    val integralList = MutableLiveData<List<IntegralListBean.dataBean.datasBean>>()
    val integralRankList = MutableLiveData<List<IntegralRankBean.dataBean.datasBean>>()

    val mineClickListener = View.OnClickListener {

        if (!checkIsLogin(it.context)) return@OnClickListener

        when(it.id){

            R.id.top_relative ->{

                if (checkIsLogin(it.context)) return@OnClickListener

                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_LOGIN)
                )

            }

            R.id.mine_collect_row ->{


                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_MINE_COLLECT)
                )

            }
            R.id.mine_integral_row ->{

                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_MINE_INTEGRAL)
                )


            }
            R.id.mine_rank_row ->{
                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_MINE_INTEGRAL_RANK)
                )

            }
            R.id.mine_setting_row ->{
                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_MINE_INTEGRAL_SETTING)
                )
            }

            R.id.setting_row ->{

                AlertDialog.Builder(it.context)
                    .setTitle("确认退出")
                    .setMessage("手滑了一下下~")
                    .setPositiveButton("确定") { _, _ -> logout(it.context) }
                    .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
                    .show()


            }
        }

    }

    /**
     * 获取积分
     */
    fun getIntegral(){

        repository.getIntegral().subscribe({

            when(it.errorCode){

                0 ->{

                    account.value = it.data!!.username
                    level.value = "lv ${it.data!!.level}"
                    mineIntegral.value = "积分：${it.data!!.coinCount}"

                }

                else ->{
                    account.value = "点击登录"
                    level.value = ""
                    mineIntegral.value = ""
                }

            }
        },{}).addTo(co)
    }



    /**
     * 获取收藏列表
     */
    fun getCollectList(){

        repository.getCollectList().subscribe({

            when(it.errorCode){

                0 ->{
                    collectList.value = it.data!!.datas
                }

                else ->{
                    toast(it.errorMsg)
                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 我的积分
     */
    fun getIntegralList(){

        repository.getIntegralList().subscribe({

            when(it.errorCode){

                0 ->{

                    integralList.value = it.data!!.datas

                }
            }
        },{}).addTo(co)


    }

    /**
     * 积分排行榜
     */
    fun getIntegralRank(){

        repository.getIntegralRank().subscribe({

            when(it.errorCode){

                0 ->{
                    integralRankList.value = it.data!!.datas
                }
            }
        },{

        }).addTo(co)
    }

    /**
     * 退出登录
     */
    fun logout(context: Context){

        repository.logout().subscribe({

            when(it.errorCode){

                0 ->{
                    toast("退出成功")
                    SharedHelper.getEdit { sp -> sp.putBoolean(Constants.SP.IS_LOGIN,false) }
                    CookieManager.getInstance().clearAllCookie()
                    (context as Activity).finish()

                }
            }
        },{

        }).addTo(co)
    }

    /**
     * 收藏
     */
    fun collect(id : Int){

        repository.collect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    setCollectState.onCollect(true)
                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 取消收藏
     */
    fun mineUnCollect(id : Int){

        repository.mineUnCollect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    setCollectState.onCollect(false)
                }
            }


        },{

        }).addTo(co)
    }
}