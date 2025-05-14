package com.example.movies3.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movies3.database.MoviesDatabase
import com.example.movies3.database.MoviesEntity

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesDao = MoviesDatabase.getInstance(application).moviesDao()

    fun getMovies(): LiveData<List<MoviesEntity>>? = moviesDao?.getMovies()

}