package com.example.literalnon.surfapp.network.response

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import java.util.*

class User(
        var phoneNumber: String? = null,
        var password: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var eMail: String? = null,
        var position: String? = null,
        var idPlace: String? = null,
        var id: Long = 0
)