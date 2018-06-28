package com.example.literalnon.surfapp.base.mvp

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import com.example.literalnon.surfapp.base.navigation.INavigationParent
import com.example.literalnon.surfapp.base.navigation.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import net.frakbot.jumpingbeans.JumpingBeans
import retrofit2.Call


/**
 * Created by bloold on 01.04.18.
 */
interface IView <Presenter> {
    var presenter: Presenter

    fun getNavigationParent(): INavigationParent

    var jumpingBeans: JumpingBeans?
    var _tvLoading: TextView?

    fun showLoadingDialog(message: String? = null) {
        if (_tvLoading != null) {
            _tvLoading!!.visibility = View.VISIBLE
            jumpingBeans = JumpingBeans.with(_tvLoading!!)
                    .makeTextJump(0, _tvLoading!!.text?.length ?: 0)
                    .setIsWave(true)
                    .setLoopDuration(1200)
                    .build()
        }

    }

    fun dismissLoadingDialog() {
        if (jumpingBeans != null) {
            jumpingBeans?.stopJumping()
            _tvLoading?.visibility = View.GONE
        }
    }
}

interface IPresenter <View, Model> {
    var view: View?
    var model: Model
    val subscriptions: CompositeDisposable

    fun attachView(view: View)
    fun detachView(view: View)

    fun getNavigator(): Navigator?

    fun onDestroyView()

    val requestQueue: HashSet<Call<*>>

    fun cancelOnDestroy(call: Call<*>) {
        requestQueue.add(call)
    }

    fun removeRequest(call: Call<*>) {
        requestQueue.remove(call)
    }

    fun unsubscribeOnDestroy(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    private fun clearRequestQueue() {
        subscriptions.clear()

        if (requestQueue.isEmpty()) {
            return
        }

        for (call in requestQueue) {
            call.cancel()
        }

        requestQueue.clear()
    }
}

interface IModel {
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}