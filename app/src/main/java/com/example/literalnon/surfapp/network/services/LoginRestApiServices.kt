package com.example.literalnon.surfapp.network.services

import com.example.literalnon.surfapp.network.response.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface LoginRestApiServices {

    @POST("/login")
    fun getSports(@Body user: User): Observable<ResponseBody>
}