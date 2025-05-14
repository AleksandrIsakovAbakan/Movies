package com.example.movies3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies3.adapter.ReviewAdapter
import com.example.movies3.adapter.TrailersAdapter
import com.example.movies3.api.Doc
import com.example.movies3.database.MoviesEntity
import com.example.movies3.model.MovieDetailViewModel


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var imageViewPoster: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewYear: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var recyclerViewTrailers: RecyclerView
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var recyclerViewReviews: RecyclerView
    private lateinit var imageViewStar: ImageView


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        initView()

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("Movie", Doc::class.java)!!
        else
            intent.getSerializableExtra("Movie") as Doc

        getMovie(movie)

        val startOff = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
        val startOn = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
        viewModel.getFavoriteMovies(movie.getId())?.observe(this) {
            if (it == null) {
                imageViewStar.setImageDrawable(startOff)
                imageViewStar.setOnClickListener {
                    viewModel.addMoviesToDatabase(
                        docToMovieEntity(
                            movie
                        )
                    )
                }
            } else {
                imageViewStar.setImageDrawable(startOn)
                imageViewStar.setOnClickListener { viewModel.deleteMoviesFromDatabase(movie.getId()) }
            }
        }
    }

    private fun getMovie(movie: Doc) {
        Glide.with(this)
            .load(movie.getPoster()?.getUrl())
            .override(600, 600)
            .into(imageViewPoster)
        textViewTitle.text = movie.getName()
        textViewYear.text = movie.getYear().toString()
        textViewDescription.text = movie.getDescription()

        val id: Int? = movie.getId()
        if (id != null) {
            viewModel.loadTrailers(id)
            viewModel.loadReviews(id)
        }

        viewModel.getTrailers().observe(this) { trailersAdapter.setTrailers(it) }
        trailersAdapter = TrailersAdapter()
        recyclerViewTrailers.adapter = trailersAdapter
        trailersAdapter.setOnTrailerClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(it.getVideo()?.toUri())
            startActivity(intent)
        }

        viewModel.getReviews().observe(this) { reviewAdapter.setReviews(it) }
        reviewAdapter = ReviewAdapter()
        recyclerViewReviews.adapter = reviewAdapter

    }

    private fun initView() {
        imageViewPoster = findViewById(R.id.imageViewPoster)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewYear = findViewById(R.id.textViewYear)
        textViewDescription = findViewById(R.id.textViewDescription)
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers)
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews)
        imageViewStar = findViewById(R.id.imageViewStar)
    }

    companion object {
        fun newIntent(context: Context, doc: Doc): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("Movie", doc)
            return intent
        }
    }

    fun docToMovieEntity(doc: Doc): MoviesEntity {
        val moviesEntity = MoviesEntity(
            id = doc.getId(),
            name = doc.getName(),
            year = doc.getYear(),
            description = doc.getDescription(),
            rating = doc.getRating()?.getKp() ?: 0.0,
            poster = doc.getPoster()?.getUrl()
        )
        return moviesEntity
    }
}
