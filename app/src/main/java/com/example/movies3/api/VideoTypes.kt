package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoTypes(
    @SerializedName("trailers")
    //@Embedded
    private val trailers: Array<Video>?
): Serializable {
    fun getTrailers(): Array<Video>? = trailers
}