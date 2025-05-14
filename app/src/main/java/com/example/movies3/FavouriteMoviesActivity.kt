package com.example.movies3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies3.adapter.MoviesAdapter
import com.example.movies3.api.Doc
import com.example.movies3.api.Poster
import com.example.movies3.api.Rating
import com.example.movies3.database.MoviesEntity
import com.example.movies3.model.FavouriteViewModel

class FavouriteMoviesActivity : AppCompatActivity() {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var recyclerViewFavourite: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)
        initView()

        moviesAdapter = MoviesAdapter()
        recyclerViewFavourite.layoutManager = GridLayoutManager(this, 2)
        recyclerViewFavourite.adapter = moviesAdapter

        favouriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        favouriteViewModel.getMovies()
            ?.observe(this) { moviesAdapter.setMovies(movieEntityToDoc(it)) }

        moviesAdapter.setOnMoveClickListener {
            val intent: Intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }
        moviesAdapter.setOnReachEndListener { null }
    }

    private fun initView() {
        recyclerViewFavourite = findViewById(R.id.recyclerViewFavourite)
    }

    fun movieEntityToDoc(moviesEntityList: List<MoviesEntity>): List<Doc> {
        val listDoc = mutableListOf<Doc>()
        for (a in moviesEntityList) {
            val doc = Doc(
                id = a.getId(),
                name = a.getName(),
                year = a.getYear(),
                description = a.getDescription(),
                rating = Rating(a.getRating()?.toDouble()),
                poster = Poster(a.getPoster().toString(), ""),
                videos = null
            )
            listDoc.add(doc)
        }
        return listDoc.toList()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavouriteMoviesActivity::class.java)
        }
    }
}