package com.capstone.nusalingo.retrofit

import com.capstone.nusalingo.ContentModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/api/contents")
    fun data(): Call<ContentModel>
}