package com.example.movies3.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Doc(
    @SerializedName("id")
    private var id: Int,

    @SerializedName("name")
    private var name: String?,

    @SerializedName("year")
    private var year: Int?,

    @SerializedName("description")
    private var description: String?,

    @SerializedName("rating")
    private var rating: Rating?,

    @SerializedName("poster")
    private var poster: Poster?,

    @SerializedName("videos")
    private var videos: VideoTypes?
) : Serializable {
    fun getRating(): Rating? = rating
    fun getPoster(): Poster? = poster
    fun getName(): String? = name
    fun getYear(): Int? = year
    fun getDescription(): String? = description
    fun getVideos(): VideoTypes? = videos
    fun getId(): Int = id
}