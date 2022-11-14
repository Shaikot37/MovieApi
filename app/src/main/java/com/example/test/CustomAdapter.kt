package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter (private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_info, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground)//ItemsViewModel.image)
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500"+itemsViewModel.image).centerCrop().into(holder.imageView)


        // sets the title to the textview from our itemHolder class
        holder.title.text = itemsViewModel.title

        // sets the description to the textview from our itemHolder class
        holder.description.text = itemsViewModel.description

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.movieImage)
        val title: TextView = itemView.findViewById(R.id.movieTitle)
        val description: TextView = itemView.findViewById(R.id.movieDescription)
    }
}