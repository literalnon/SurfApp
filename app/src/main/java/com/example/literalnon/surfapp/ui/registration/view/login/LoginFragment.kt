package com.example.literalnon.surfapp.ui.registration.view.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.literalnon.surfapp.R
import com.example.literalnon.surfapp.base.navigation.INavigationParent
import com.example.literalnon.surfapp.ui.registration.view.login.mvp.ILoginFragmentPresenter
import com.example.literalnon.surfapp.ui.registration.view.login.mvp.ILoginFragmentView
import kotlinx.android.synthetic.main.fragment_login.*
import net.frakbot.jumpingbeans.JumpingBeans

class LoginFragment : Fragment(), ILoginFragmentView {

    override var jumpingBeans: JumpingBeans? = null
    override var _tvLoading: TextView? = null

    private var screenComponent: ILoginScreenComponent = DaggerILoginScreenComponent.builder().build()
    override var presenter: ILoginFragmentPresenter = screenComponent.getPresenter()

    companion object {
        fun newInstance() =
                LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        btnNext.setOnClickListener {
            presenter.login(etPhone.text.toString(), etPassword.text.toString())
        }
    }

    override fun registrationSuccess() {
        Toast.makeText(context, "успешная регистрация", Toast.LENGTH_LONG).show()
    }

    override fun getNavigationParent(): INavigationParent {
        return activity as INavigationParent
    }

}
