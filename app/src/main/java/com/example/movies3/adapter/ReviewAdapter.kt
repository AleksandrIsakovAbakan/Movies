package com.example.movies3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies3.R
import com.example.movies3.api.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewsViewHolder>() {

    private var reviews: List<Review> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setReviews(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item, parent, false)

        return ReviewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {

        val review: Review = reviews[position]
        holder.textViewReviews.text = buildString {
            append(review.getAuthor())
            append("\n\n")
            append(review.getReview())
        }
        val backgroundId = setBackground(review.getType())
        val background = ContextCompat.getColor(holder.itemView.context, backgroundId)
        holder.linearLayoutContainer.setBackgroundColor(background)
    }

    class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewReviews: TextView = itemView.findViewById(R.id.textViewReview)
        val linearLayoutContainer: LinearLayout = itemView.findViewById(R.id.linerLayoutContainer)
    }

    private fun setBackground(type: String): Int {
        val backgroundId: Int = when (type) {
            "Позитивный" -> android.R.color.holo_green_light
            "Негативный" -> android.R.color.holo_red_light
            else -> android.R.color.holo_orange_light
        }
        return backgroundId
    }

}