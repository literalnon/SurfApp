package com.example.literalnon.surfapp.ui.registration.view.login.mvp

import com.example.literalnon.surfapp.base.mvp.DaggerPresenterDependencyComponent
import com.example.literalnon.surfapp.base.mvp.IPresenter
import com.example.literalnon.surfapp.base.navigation.Navigator
import com.example.literalnon.surfapp.ui.registration.view.login.DaggerILoginScreenComponent
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import java.util.*
import javax.inject.Inject

/**
 * Created by dmitry on 04.05.18.
 */
interface ILoginFragmentPresenter : IPresenter<ILoginFragmentView, ILoginFragmentModel> {
    fun login(login: String, password: String)
}

class LoginFragmentPresenter @Inject constructor() : ILoginFragmentPresenter {

    private val dependencyComponent = DaggerPresenterDependencyComponent.builder().build()
    private val screenComponent = DaggerILoginScreenComponent.builder().build()

    override val subscriptions: CompositeDisposable = dependencyComponent.getSubscriptions()
    override val requestQueue: HashSet<Call<*>> = dependencyComponent.getRequestQueue()

    override var view: ILoginFragmentView? = null
    override var model: ILoginFragmentModel = screenComponent.getModel()

    override fun login(login: String, password: String) {
        view?.showLoadingDialog()

        model.login(login, password)
                .doAfterTerminate { view?.dismissLoadingDialog() }
                .subscribe({
                    view?.registrationSuccess()
                }, {
                    it.printStackTrace()
                })
    }

    override fun attachView(view: ILoginFragmentView) {
        this.view = view
    }

    override fun detachView(view: ILoginFragmentView) {
        if (this.view?.equals(view) == true) {
            this.view = null
        }
    }

    override fun getNavigator(): Navigator? {
        return view?.getNavigationParent()?.navigator
    }

    override fun onDestroyView() {

    }

}
