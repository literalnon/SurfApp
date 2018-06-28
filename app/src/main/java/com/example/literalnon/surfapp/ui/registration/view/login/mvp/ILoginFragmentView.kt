package com.example.literalnon.surfapp.ui.registration.view.login.mvp

import com.example.literalnon.surfapp.base.mvp.IView


/**
 * Created by dmitry on 04.05.18.
 */

interface ILoginFragmentView : IView<ILoginFragmentPresenter> {
    fun registrationSuccess()
}
