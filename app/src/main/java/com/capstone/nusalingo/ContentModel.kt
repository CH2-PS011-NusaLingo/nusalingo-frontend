package com.capstone.nusalingo

data class ContentModel (
    val result: ArrayList<Result>
) {
    data class Result (val contentId: Int, val image: String, val title: String, val desc_one: String, val desc_two: String, val desc_three: String, val desc_four: String, val desc_five: String, val desc_six: String)
}