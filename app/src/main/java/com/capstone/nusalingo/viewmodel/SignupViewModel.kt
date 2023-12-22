package com.capstone.nusalingo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.nusalingo.data.api.request.SignupRequest
import com.capstone.nusalingo.data.api.response.BaseResponse
import com.capstone.nusalingo.data.api.response.SignupResponse
import com.capstone.nusalingo.repository.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val signupResult: MutableLiveData<BaseResponse<SignupResponse>> = MutableLiveData()

    fun signupUser(name: String, email: String, pwd: String) {

        signupResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val signupRequest = SignupRequest(
                    password = pwd,
                    email = email,
                    name = name
                )
                val response = userRepo.signupUser(signupRequest = signupRequest)
                if (response?.code() == 200) {
                    signupResult.value = BaseResponse.Success(response.body())
                } else {
                    signupResult.value = BaseResponse.Error(response?.body())
                }

            } catch (ex: Exception) {
                signupResult.value = BaseResponse.Error()
            }
        }
    }
}