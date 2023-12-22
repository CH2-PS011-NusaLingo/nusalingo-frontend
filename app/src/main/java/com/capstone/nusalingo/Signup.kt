package com.capstone.nusalingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.nusalingo.data.api.response.BaseResponse
import com.capstone.nusalingo.data.api.response.SignupResponse
import com.capstone.nusalingo.databinding.ActivitySignupBinding
import com.capstone.nusalingo.utils.SessionManager
import com.capstone.nusalingo.viewmodel.SignupViewModel

class Signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        viewModel.signupResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processSignup(it.data)
                    navigateToHome()
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

        binding.btnSignup.setOnClickListener {
            doSignup()
        }
        binding.txtSignin.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }


    }

    private fun navigateToHome() {
        val intent = Intent(this, Signin::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doSignup() {
        val name = binding.txtInputName.text.toString()
        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()
        viewModel.signupUser(name = name, email = email, pwd = pwd)

    }


    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processSignup(data: SignupResponse?) {
        showToast("" + data?.message)
    }

    fun processError(data: SignupResponse?) {
        showToast("" + data?.message)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}