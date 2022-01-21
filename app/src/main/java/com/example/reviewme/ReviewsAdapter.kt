package com.example.reviewme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewme.classes.Review

class ReviewsAdapter : RecyclerView.Adapter<ReviewItemViewHolder>() {
    var data: List<Review> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.reviews_list_item, parent, false)

        return ReviewItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        holder.userName.text = data[position].author_name
        holder.motivation.text = data[position].text
        holder.rating.text = data[position].rating.toString()
        holder.timeDescription.text = data[position].relative_time_description
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ReviewItemViewHolder(reviewView: View) : RecyclerView.ViewHolder(reviewView) {
    var userName: TextView = reviewView.findViewById(R.id.user_name)
    var motivation: TextView = reviewView.findViewById(R.id.motivation)
    var rating: TextView = reviewView.findViewById(R.id.rating)
    var timeDescription: TextView = reviewView.findViewById(R.id.time_description)
}
