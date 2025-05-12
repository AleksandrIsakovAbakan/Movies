package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ReviewsRs(
    @SerializedName("docs")
    private val docs: Array<Review>,
    @SerializedName("total")
    private val total: Int,
    @SerializedName("limit")
    private val limit: Int,
    @SerializedName("page")
    private val page: Int,
    @SerializedName("pages")
    private val pages: Int
): Serializable {
    fun getReviews(): Array<Review> = docs
}