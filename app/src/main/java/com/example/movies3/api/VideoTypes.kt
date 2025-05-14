package com.example.movies3.api

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoTypes(
    @SerializedName("trailers")
    @TypeConverters
    private val trailers: List<Video> = listOf<Video>()
) : Serializable {
    fun getTrailers(): List<Video> = trailers
}