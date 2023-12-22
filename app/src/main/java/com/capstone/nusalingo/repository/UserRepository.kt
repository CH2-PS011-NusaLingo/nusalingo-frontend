package com.capstone.nusalingo.repository

import com.capstone.nusalingo.data.api.methods.UserApi
import com.capstone.nusalingo.data.api.request.SigninRequest
import com.capstone.nusalingo.data.api.request.SignupRequest
import com.capstone.nusalingo.data.api.response.SigninResponse
import com.capstone.nusalingo.data.api.response.SignupResponse
import retrofit2.Response

class UserRepository {

    suspend fun signinUser(signinRequest: SigninRequest): Response<SigninResponse>? {
        return  UserApi.getApi()?.signinUser(signinRequest = signinRequest)
    }

    suspend fun signupUser(signupRequest: SignupRequest): Response<SignupResponse>? {
        return UserApi.getApi()?.signupUser(signupRequest = signupRequest)
    }
}