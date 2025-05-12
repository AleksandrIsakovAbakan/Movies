package com.example.movies3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies3.R
import com.example.movies3.api.Video

class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    private var trailers: List<Video> = arrayListOf()
    private lateinit var onTrailerClickListener: OnTrailerClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setTrailers(trailers: List<Video>) {
        this.trailers = trailers
        notifyDataSetChanged()
    }

    fun setOnTrailerClickListener(onTrailerClickListener: OnTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.trailer_item, parent, false)

        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {

        val video: Video = trailers[position]
        holder.textViewTrailers.text = video.getNameVideo()
        holder.itemView.setOnClickListener { onTrailerClickListener.onTrailerClick(video) }
    }

    class TrailersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val textViewTrailers: TextView = itemView.findViewById(R.id.textViewTrailers)
    }

    fun interface OnTrailerClickListener {
        fun onTrailerClick(video: Video)
    }
}