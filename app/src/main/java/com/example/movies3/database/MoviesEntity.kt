package com.example.movies3.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_entity")
data class MoviesEntity(
    @SerializedName("id")
    @PrimaryKey
    private val id: Int,
    @SerializedName("name")
    private val name: String?,
    @SerializedName("year")
    private val year: Int?,
    @SerializedName("description")
    private val description: String?,
    @SerializedName("rating")
    private val rating: Double?,
    @SerializedName("poster")
    private val poster: String?,
) {
    fun getPoster(): String? = poster
    fun getRating(): Double? = rating
    fun getName(): String? = name
    fun getYear(): Int? = year
    fun getDescription(): String? = description
    fun getId(): Int = id

}
