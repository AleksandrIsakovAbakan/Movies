package com.example.movies3.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie?field=rating.kp&search=1-10&sortField=votes.kp&sortType=-1&limit=40")
    @Headers(
        "Accept: application/json",
        "X-API-KEY: XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX"
    )
    fun loadMovies(@Query("page") page: Int): Single<MovieRs>

    @GET("movie/{idFilms}")
    @Headers(
        "Accept: application/json",
        "X-API-KEY: XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX"
    )
    fun getTrailers(@Path("idFilms") idFilms: Int): Single<Doc>?

    @GET("review?page=1&limit=10&sortField=date&sortType=-1")
    @Headers(
        "Accept: application/json",
        "X-API-KEY: XXXXXXX-XXXXXXX-XXXXXXX-XXXXXXX"
    )
    fun getReviews(@Query("movieId") idFilms: Int): Single<ReviewRs>?

}
