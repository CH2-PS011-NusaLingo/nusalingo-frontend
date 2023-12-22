package com.capstone.nusalingo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.nusalingo.data.api.request.SigninRequest
import com.capstone.nusalingo.data.api.response.BaseResponse
import com.capstone.nusalingo.data.api.response.SigninResponse
import com.capstone.nusalingo.repository.UserRepository
import kotlinx.coroutines.launch

class SigninViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val signinResult: MutableLiveData<BaseResponse<SigninResponse>> = MutableLiveData()

    fun signinUser(email: String, pwd: String) {

        signinResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val signinRequest = SigninRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.signinUser(signinRequest = signinRequest)
                if (response?.code() == 200) {
                    signinResult.value = BaseResponse.Success(response.body())
                } else {
                    signinResult.value = BaseResponse.Error(response?.body())
                }

            } catch (ex: Exception) {
                signinResult.value = BaseResponse.Error()
            }
        }
    }
}