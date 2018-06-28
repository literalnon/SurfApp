package com.example.literalnon.surfapp.ui.registration.view.login

import com.example.literalnon.surfapp.ui.registration.view.login.mvp.LoginFragmentModel
import com.example.literalnon.surfapp.ui.registration.view.login.mvp.LoginFragmentPresenter
import dagger.Component

/**
 * Created by dmitry on 07.05.18.
 */
@Component
interface ILoginScreenComponent {
    fun getPresenter(): LoginFragmentPresenter
    fun getModel(): LoginFragmentModel
}