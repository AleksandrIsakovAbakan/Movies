package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Poster(
    @SerializedName("url")
    private val url: String,
    @SerializedName("previewUrl")
    private val previewUrl: String
) : Serializable {
    fun getUrl(): String = url
    fun getPreviewUrl() = previewUrl
}