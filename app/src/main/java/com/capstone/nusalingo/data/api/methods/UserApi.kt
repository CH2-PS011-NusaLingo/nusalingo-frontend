package com.capstone.nusalingo.data.api.methods

import com.capstone.nusalingo.data.api.ApiClient
import com.capstone.nusalingo.data.api.request.SigninRequest
import com.capstone.nusalingo.data.api.request.SignupRequest
import com.capstone.nusalingo.data.api.response.SigninResponse
import com.capstone.nusalingo.data.api.response.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/api/auth/signin")
    suspend fun signinUser(@Body signinRequest: SigninRequest): Response<SigninResponse>

    @POST("api/auth/signup")
    suspend fun signupUser(@Body signupRequest: SignupRequest): Response<SignupResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}