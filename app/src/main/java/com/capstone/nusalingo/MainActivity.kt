package com.capstone.nusalingo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.capstone.nusalingo.databinding.ActivityMainBinding
import com.capstone.nusalingo.utils.SessionManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.btnActivity.setOnClickListener {
            val intent = Intent(this, Content::class.java)
            startActivity(intent)
        }

        binding.btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            SessionManager.clearData(this)
            val intent = Intent(this, Signin::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}