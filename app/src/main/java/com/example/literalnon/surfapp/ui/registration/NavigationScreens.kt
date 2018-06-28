package com.example.literalnon.surfapp.ui.registration

import com.example.literalnon.surfapp.base.navigation.IBaseItem
import com.example.literalnon.surfapp.ui.registration.view.login.LoginFragment

enum class NavigationScreens : IBaseItem {
    LOGIN_SCREEN {
        override var data: Any? = null

        override fun getTag() = "LOGIN_SCREEN"

        override fun getPreviousEnumObject() = null

        override fun getFragment() = LoginFragment.newInstance()
    }
}