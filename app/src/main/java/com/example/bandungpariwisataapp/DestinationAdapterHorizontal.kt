package com.example.bandungpariwisataapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bandungpariwisataapp.R
import com.example.bandungpariwisataapp.models.Destination

class DestinationAdapterHorizontal(
    private val destinations: List<Destination>,
    private val onClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationAdapterHorizontal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destination_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount() = destinations.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val destinationImage: ImageView = itemView.findViewById(R.id.destinationImage)
        private val destinationName: TextView = itemView.findViewById(R.id.destinationName)

        fun bind(destination: Destination) {
            destinationName.text = destination.name
            Glide.with(itemView.context)
                .load(destination.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(destinationImage)

            itemView.setOnClickListener { onClick(destination) }
        }
    }
}
