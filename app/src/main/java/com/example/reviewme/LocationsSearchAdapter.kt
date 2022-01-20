package com.example.reviewme

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewme.classes.Location
import org.w3c.dom.Text

class LocationsSearchAdapter: RecyclerView.Adapter<LocationItemViewHolder>() {

    var data: List<Location> = listOf<Location>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item_view, parent, false) as TextView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return LocationItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) {
        holder.itemTitle.text = data[position].name
        holder.itemDescription.text = data[position].formatted_address
        holder.itemRating.text = data[position].rating.toString()
        holder.itemStatus.text = data[position].status
        if (data[position].status == "Closed") {
            holder.itemStatus.setTextColor(Color.rgb(222, 1, 23))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class LocationItemViewHolder(val locationView: View): RecyclerView.ViewHolder(locationView) {
    var itemTitle : TextView = locationView.findViewById(R.id.item_title)
    var itemDescription: TextView = locationView.findViewById(R.id.item_description)
    var itemRating: TextView = locationView.findViewById(R.id.item_rating)
    var itemStatus: TextView = locationView.findViewById(R.id.item_status)

}
