package com.capstone.nusalingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.nusalingo.data.api.response.BaseResponse
import com.capstone.nusalingo.data.api.response.SigninResponse
import com.capstone.nusalingo.databinding.ActivitySigninBinding
import com.capstone.nusalingo.utils.SessionManager
import com.capstone.nusalingo.viewmodel.SigninViewModel

class Signin : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private val viewModel by viewModels<SigninViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        viewModel.signinResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    stopLoading()
                    processError(it.message)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }


    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()
        viewModel.signinUser(email = email, pwd = pwd)

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processLogin(data: SigninResponse?) {
        showToast("" + data?.message)
        if (!data?.data?.token.isNullOrEmpty()) {
            data?.data?.token?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    fun processError(data: SigninResponse?) {
        showToast("Gagal Masuk!" + data?.message)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}