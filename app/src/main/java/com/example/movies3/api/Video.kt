package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Video(
    @SerializedName("url")
    private val url: String?,
    @SerializedName("name")
    private val name: String?,
//    @SerializedName("site")
//    private val site: String?,
//    @SerializedName("size")
//    private val size: String?,
//    @SerializedName("type")
//    private val type: String?,
): Serializable {
    fun getVideo(): String? = url
    fun getNameVideo(): String? = name
}