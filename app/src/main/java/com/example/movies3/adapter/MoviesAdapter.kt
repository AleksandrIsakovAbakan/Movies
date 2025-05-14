package com.example.movies3.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies3.R
import com.example.movies3.api.Doc
import kotlin.math.roundToInt

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var movies: List<Doc> = arrayListOf()
    private lateinit var onReachEndListener: OnReachEndListener
    private lateinit var onMoveClickListener: OnMoveClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(movies: List<Doc>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun setOnMoveClickListener(onMoveClickListener: OnMoveClickListener) {
        this.onMoveClickListener = onMoveClickListener
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val movie: Doc = movies[position]
        val rating: Double? = movie.getRating()?.getKp()

        val kp = if (rating != null) {
            rating.times(10).roundToInt() / 10.0
        } else 0.0
        val backgroundId = setBackground(rating)

        Glide.with(holder.itemView)
            .asDrawable()
            .load(movie.getPoster()?.getUrl() ?: R.drawable.baseline_error_36)
            .into(holder.imageViewPoster)

        val background: Drawable? = ContextCompat.getDrawable(holder.itemView.context, backgroundId)

        holder.textViewRating.background = background
        holder.textViewRating.text = kp.toString()
        holder.itemView.setOnClickListener { onMoveClickListener.onMoveClick(movie) }

        if (position >= movies.size - 10) onReachEndListener.onReachEnd()
    }

    private fun setBackground(rating: Double?): Int {
        var backgroundId = R.drawable.ic_launcher_foreground

        if (rating != null) {
            backgroundId = when {
                rating > 7 -> R.drawable.circle_green
                rating > 5 -> R.drawable.circle_orange
                else -> R.drawable.circle_red
            }
        }

        return backgroundId
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        var textViewRating: TextView = itemView.findViewById(R.id.textViewRating)
    }

    fun interface OnReachEndListener {
        fun onReachEnd()
    }

    fun interface OnMoveClickListener {
        fun onMoveClick(doc: Doc)
    }
}