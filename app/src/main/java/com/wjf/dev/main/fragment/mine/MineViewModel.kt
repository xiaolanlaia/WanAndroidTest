package com.wjf.dev.main.fragment.mine

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.util.addTo
import com.wjf.dev.util.toast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.mine_fragment.view.*
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

    val mineClickListener = View.OnClickListener {

        when(it.id){

            R.id.mine_account ->{

                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_LOGIN)
                )

            }

            R.id.mine_collect_row ->{
                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_COLLECT)
                )


            }
            R.id.mine_integral_row ->{

            }
            R.id.mine_rank_row ->{

            }
            R.id.mine_setting_row ->{

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

            }
        },{}).addTo(co)
    }
}