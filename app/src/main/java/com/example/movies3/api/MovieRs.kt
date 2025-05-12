package com.example.movies3.api

import com.google.gson.annotations.SerializedName

class MovieRs(
    @SerializedName("docs")
    private val docs: List<Doc>,
    @SerializedName("total")
    private val total: Int,
    @SerializedName("limit")
    private val limit: Int,
    @SerializedName("page")
    private val page: Int,
    @SerializedName("pages")
    private val pages: Int
){
    fun getDocs(): List<Doc> = docs
}