package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Video(
    @SerializedName("url")
    private val url: String?,
    @SerializedName("name")
    private val name: String?,
) : Serializable {
    fun getVideo(): String? = url
    fun getNameVideo(): String? = name
}