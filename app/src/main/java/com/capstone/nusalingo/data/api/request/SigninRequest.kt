package com.capstone.nusalingo.data.api.request

import com.google.gson.annotations.SerializedName

data class SigninRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)