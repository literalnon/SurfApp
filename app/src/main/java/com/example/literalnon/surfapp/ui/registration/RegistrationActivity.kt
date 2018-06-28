package com.example.literalnon.surfapp.ui.registration

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.literalnon.surfapp.R

import com.example.literalnon.surfapp.base.navigation.INavigationParent
import com.example.literalnon.surfapp.base.navigation.Navigator
import com.example.literalnon.surfapp.base.navigation.ReplaceStrategy
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : AppCompatActivity(), INavigationParent {

    override var navigator: Navigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        toolbar.title = ""

        navigator = Navigator.Builder()
                .firstFragment(NavigationScreens.LOGIN_SCREEN)
                .strategy(ReplaceStrategy(supportFragmentManager, R.id.container))
                .build()

        navigator?.openFirstFragment()

    }

    override fun onBackPressed() {
        if (navigator?.backNavigation() != true){
            super.onBackPressed()
        }
    }

}
