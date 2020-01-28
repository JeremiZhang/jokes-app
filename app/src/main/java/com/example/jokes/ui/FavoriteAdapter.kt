package com.example.jokes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.R
import com.example.jokes.model.Favorite
import kotlinx.android.synthetic.main.item_joke.view.*

class FavoriteAdapter(private val favorite: List<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating into our custom item layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorite[position])
    }

    /**
     * Binds data in recyclerviewer
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(favorite: Favorite) {
            itemView.tvFavoriteSetup.text = favorite.setup
            itemView.tvFavoriteAnswer.text = favorite.answer
        }

    }
}