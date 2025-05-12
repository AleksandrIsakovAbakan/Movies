package com.example.movies3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies3.adapter.ReviewAdapter
import com.example.movies3.adapter.TrailersAdapter
import com.example.movies3.api.Doc
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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        initView()

        val movie = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("Movie", Doc::class.java)!!
        else
            intent.getSerializableExtra("Movie") as Doc

        Log.d("MovieDetailActivity-onCreate", movie.name.toString())
        Glide.with(this)
            .load(movie.poster?.getUrl())
            .override(600, 600)
            .into(imageViewPoster)
        textViewTitle.text = movie.name
        textViewYear.text = movie.year.toString()
        textViewDescription.text = movie.description

        val id: Int? = movie?.id
        if (id != null){
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

        viewModel.getReviews().observe(this) {
            reviewAdapter.setReviews(it)
        }
        reviewAdapter = ReviewAdapter()
        recyclerViewReviews.adapter = reviewAdapter

        //val movieDao: MoviesDao = MoviesDatabase.getInstance(application).moviesDao()
        //if (movie != null) movieDao.add(docToMovieEntity(movie))

    }

    private fun initView() {
        imageViewPoster = findViewById(R.id.imageViewPoster)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewYear = findViewById(R.id.textViewYear)
        textViewDescription = findViewById(R.id.textViewDescription)
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers)
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews)
    }

    companion object {
        fun newIntent(context: Context, doc: Doc): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("Movie", doc)
            return intent
        }
    }

//    fun docToMovieEntity(doc: Doc): MoviesEntity {
//        val moviesEntity: MoviesEntity = MoviesEntity(
//            id = doc.getId(),
//            name = doc.getName(),
//            year = doc.getYear(),
//            description = doc.getDescription(),
//            rating = doc.getRating()?.getKp(),
//            poster = doc.getPoster()?.getUrl(),
//            videos = ""
//        )
//        return moviesEntity
//    }
}