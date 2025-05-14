package com.example.movies3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies3.adapter.MoviesAdapter
import com.example.movies3.database.MoviesDao
import com.example.movies3.database.MoviesDatabase
import com.example.movies3.model.MainViewModel
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesDao: MoviesDao

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        moviesDao = MoviesDatabase.getInstance(application).moviesDao()!!
        thread { moviesDao.size() }

        moviesAdapter = MoviesAdapter()
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        recyclerViewMovies.adapter = moviesAdapter

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getMovies().observe(this) { moviesAdapter.setMovies(it) }

        moviesAdapter.setOnReachEndListener { mainViewModel.loadMovies() }

        moviesAdapter.setOnMoveClickListener {
            val intent: Intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }

        mainViewModel.getIsLoading().observe(this) {
            if (it == true) progressBar.visibility = View.VISIBLE else progressBar.visibility =
                View.GONE
        }
    }

    private fun initView() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies)
        progressBar = findViewById(R.id.progressBarLoading)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemFavourite) {
            val intent = FavouriteMoviesActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}