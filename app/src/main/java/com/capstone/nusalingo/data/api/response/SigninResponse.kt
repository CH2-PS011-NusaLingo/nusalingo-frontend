package com.capstone.nusalingo.data.api.response

import com.google.gson.annotations.SerializedName

data class SigninResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("email")
        var email: String,
        @SerializedName("id")
        var id: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("avatar")
        var avatar: String,
        @SerializedName("token")
        var token: String,
        @SerializedName("level")
        var level: Int,
        @SerializedName("total_pts")
        var total_pts: Int,
        @SerializedName("exp")
        var exp: Int
    )
}