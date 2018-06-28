package com.example.literalnon.surfapp.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by bloold on 29.11.17.
 */
class BearerAuthorizationInterceptor : Interceptor {

    companion object {
        val TAG = "BearerAuthorization"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        /*val token = LoginController.instance.access_token

        if (BuildConfig.DEBUG) {
            Log.d("tag", "start")
            Log.d(Const.Headers.EXTRA_CONTENT_TYPE, "application/x-www-form-urlencoded")
            Log.d(Const.Headers.EXTRA_ACCESS_TOKEN, LoginController.instance.access_token)
            Log.d(Const.Headers.EXTRA_CLIENT, LoginController.instance.client)
            Log.d(Const.Headers.EXTRA_EXPIRY, LoginController.instance.expiry)
            Log.d(Const.Headers.EXTRA_TOKEN_TYPE, LoginController.instance.token_type)
            Log.d(Const.Headers.EXTRA_UID, LoginController.instance.uid)
            Log.d("tag", "end")
        }*/

        try {
            val builder = chain.request().newBuilder()

            //if (token?.isEmpty() == false) {
                builder.addHeader("Content-Type", "application/json")

                try {
                    return chain.proceed(builder.build())
                } catch (e: IOException) {

                }

            //}

            return chain.proceed(chain.request().newBuilder().build())
        } catch (e: SocketTimeoutException) {
            return chain.proceed(chain.request())
        }

    }
}
