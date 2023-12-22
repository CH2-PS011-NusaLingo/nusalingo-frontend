package com.capstone.nusalingo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.nusalingo.databinding.ActivityContentBinding
import com.capstone.nusalingo.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Content : AppCompatActivity() {
    private val TAG: String = "Content"
    private lateinit var binding: ActivityContentBinding
    private lateinit var contentAdapter: ContentAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.title = "Aktivitas"

        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        contentAdapter = ContentAdapter(arrayListOf(), object : ContentAdapter.OnAdapterListener {
            override fun onClick(result: ContentModel.Result) {
                startActivity(
                    Intent(this@Content, DetailContent::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_image", result.image)
                        .putExtra("intent_descone", result.desc_one)
                        .putExtra("intent_desctwo", result.desc_two)
                        .putExtra("intent_descthree", result.desc_three)
                        .putExtra("intent_descfour", result.desc_four)
                        .putExtra("intent_descfive", result.desc_five)
                        .putExtra("intent_descsix", result.desc_six)
                )
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contentAdapter
        }
    }

    private fun getDataFromApi() {
        showLoading(true)
        ApiService.endpoint.data()
            .enqueue(object : Callback<ContentModel> {
                override fun onFailure(call: Call<ContentModel>, t: Throwable) {
                    printLog(t.toString())
                    showLoading(false)
                }

                override fun onResponse(
                    call: Call<ContentModel>,
                    response: Response<ContentModel>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        showResult(response.body()!!)
                    }
                }
            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showLoading(loading: Boolean) {
        when (loading) {
            true -> binding.progressBar.visibility = View.VISIBLE
            false -> binding.progressBar.visibility = View.GONE
        }
    }

    private fun showResult(results: ContentModel) {
        for (result in results.result) printLog("title: ${result.title}")
        contentAdapter.setData(results.result)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}
