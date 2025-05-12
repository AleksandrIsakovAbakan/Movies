package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("kp")
    private val kp: Double,
//    @SerializedName("imdb")
//    private val imdb: Double,
//    @SerializedName("filmCritics")
//    private val filmCritics: Double,
//    @SerializedName("russianFilmCritics")
//    private val russianFilmCritics: Double,
//    @SerializedName("await")
//    private val await: Int
): Serializable {
    fun getKp(): Double = kp
//    fun getImdb(): Double = imdb
//    fun getFilmCritics(): Double = filmCritics
//    fun getRussianFilmCritics(): Double = russianFilmCritics
//    fun getAwait(): Int = await
}