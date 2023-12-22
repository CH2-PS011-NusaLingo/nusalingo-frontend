package com.capstone.nusalingo.data.api.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String
)