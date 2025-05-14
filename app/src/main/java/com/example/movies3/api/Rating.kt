package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("kp")
    private val kp: Double?,
) : Serializable {
    fun getKp(): Double? = kp
}