package com.example.movies3.model

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies3.api.ApiFactory
import com.example.movies3.api.Review
import com.example.movies3.api.Video
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val trailers: MutableLiveData<List<Video>> = MutableLiveData()
    private val reviews: MutableLiveData<List<Review>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //private val moviesDao: MoviesDao? = MoviesDatabase.getInstance(application).moviesDao()

    fun getTrailers(): LiveData<List<Video>> = trailers

    fun getReviews(): LiveData<List<Review>> = reviews

    @SuppressLint("CheckResult")
    fun loadTrailers(id: Int){
        val disposable: Disposable? = ApiFactory.apiService.getTrailers(id)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map { it.videos!! }
            ?.subscribe({
                Log.d("loadTrailers", it.toString())
                trailers.setValue(it.getTrailers()?.toList() ?: listOf())
                        },
                { Log.d("MovieDetailActivity3", it.toString()) })
        if (disposable != null) compositeDisposable.add(disposable)
    }

    @SuppressLint("CheckResult")
    fun loadReviews(id: Int){
        val disposable: Disposable? = ApiFactory.apiService.getReviews(id)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map { it.getReviews().toList() }
            ?.subscribe({ reviews.setValue(it) },
                { Log.d("loadReviews", it.message.toString()) })
        if (disposable != null) compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

//    fun addMovie(movie: MoviesEntity) {
//        @SuppressLint("CheckResult")
//            val disposable: Disposable? = moviesDao?.add(movie)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe({ Log.d("MovieDetailViewModel", movie.toString()) },
//               { Log.d("MovieDetailViewModel", "Error fun add") })
//        if (disposable != null) compositeDisposable.add(disposable)
//    }
}