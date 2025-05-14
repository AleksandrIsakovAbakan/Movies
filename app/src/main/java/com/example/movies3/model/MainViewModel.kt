package com.example.movies3.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies3.api.ApiFactory
import com.example.movies3.api.Doc
import com.example.movies3.api.MovieRs
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesLiveData = MutableLiveData<List<Doc>>()
    private val compositeDisposable = CompositeDisposable()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var page = 1

    init {
        loadMovies()
    }

    fun getMovies(): LiveData<List<Doc>> = moviesLiveData

    fun getIsLoading(): LiveData<Boolean> = isLoading

    fun loadMovies() {

        val loading = getIsLoading().value

        if (loading != null && loading) return

        val disposable: Disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doAfterTerminate { isLoading.value = false }
            .subscribe({ loadMovies(it) }, { Log.d("MainActivity", it.toString()) })

        compositeDisposable.add(disposable)
    }

    private fun loadMovies(a: MovieRs) {

        val loadedMovies: List<Doc>? = moviesLiveData.getValue()

        if (loadedMovies != null) {
            val loadedMovies2: MutableList<Doc> = loadedMovies.toMutableList()
            loadedMovies2.addAll(a.getDocs())
            moviesLiveData.value = loadedMovies2.toList()
        } else {
            moviesLiveData.value = a.getDocs()
        }

        Log.d("MainViewModel", "Loaded: $page")
        page++
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}