package com.capstone.nusalingo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.nusalingo.databinding.ActivityDetailContentBinding

class DetailContent : AppCompatActivity() {
    private lateinit var binding: ActivityDetailContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intentTitle = intent.getStringExtra("intent_title")
        val intentImage = intent.getStringExtra("intent_image")
        val intentDescOne = intent.getStringExtra("intent_descone")
        val intentDescTwo = intent.getStringExtra("intent_desctwo")
        val intentDescThree = intent.getStringExtra("intent_descthree")
        val intentDescFour = intent.getStringExtra("intent_descfour")
        val intentDescFive = intent.getStringExtra("intent_descfive")
        val intentDescSix = intent.getStringExtra("intent_descsix")

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        binding.title.text = intentTitle
        binding.descTwo.text = intentDescTwo
        binding.descThree.text = intentDescThree
        binding.descFour.text = intentDescFour
        binding.descFive.text = intentDescFive
        binding.descSix.text = intentDescSix

        Glide.with(this)
            .load(intentImage)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(binding.imageView)
    }
}
