package com.example.reviewme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationsSearchAdapter: RecyclerView.Adapter<LocationItemViewHolder>() {

    var data: List<String> = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item_view, parent, false) as TextView

        return LocationItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class LocationItemViewHolder(val locationView: TextView): RecyclerView.ViewHolder(locationView) {
    fun bind(text: String) {
        locationView.text = text
    }
}
