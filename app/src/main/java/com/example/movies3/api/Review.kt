package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Review(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("movieId")
    private val movieId: Int,
    @SerializedName("title")
    private val title: String,
    @SerializedName("type")
    private val type: String,
    @SerializedName("review")
    private val review: String,
    @SerializedName("date")
    private val date: String,
    @SerializedName("author")
    private val author: String,
    @SerializedName("userRating")
    private val userRating: Int,
    @SerializedName("authorId")
    private val authorId: Int,
    @SerializedName("reviewLikes")
    private val reviewLikes: Int,
    @SerializedName("reviewDislikes")
    private val reviewDislikes: Int,
    @SerializedName("updatedAt")
    private val updatedAt: String,
    @SerializedName("createdAt")
    private val createdAt: String
) : Serializable {
    fun getAuthor(): String = author
    fun getReview(): String = review
    fun getType(): String = type
}
