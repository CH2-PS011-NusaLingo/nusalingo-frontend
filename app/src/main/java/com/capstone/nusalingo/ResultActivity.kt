package com.capstone.nusalingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        tvScore.text = "Total nilaimu adalah ${intent.getIntExtra(Quiz.CORRECT_ANSWERS, 0)} dari ${intent.getIntExtra(Quiz.TOTAL_QUESTIONS, 0)}"

        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}