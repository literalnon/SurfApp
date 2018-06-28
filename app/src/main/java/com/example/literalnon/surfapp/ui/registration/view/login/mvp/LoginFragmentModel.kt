package com.example.literalnon.surfapp.ui.registration.view.login.mvp

import com.example.literalnon.surfapp.base.mvp.IModel
import com.example.literalnon.surfapp.network.createService
import com.example.literalnon.surfapp.network.response.User
import com.example.literalnon.surfapp.network.services.LoginRestApiServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * Created by dmitry on 04.05.18.
 */
interface ILoginFragmentModel : IModel {
    fun login(login: String, password: String): Observable<ResponseBody>
}

class LoginFragmentModel @Inject constructor() : ILoginFragmentModel {
    private val service = createService(LoginRestApiServices::class.java)

    override fun login(login: String, password: String): Observable<ResponseBody> {
        return service.getSports(User(login, password,"firstName",
                "lastName",
        "eMail",
        "position",
        "idPlace"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

    }

}


