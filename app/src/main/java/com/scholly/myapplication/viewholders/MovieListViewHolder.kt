package com.scholly.myapplication.viewholders

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.R
import com.scholly.myapplication.model.Movie
import com.squareup.picasso.Picasso

class MovieListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.cell_movie_list, parent, false)) {
    private var textTitle: TextView? = null
    private var imageAvatar: ImageView? = null
    private var textDescription: TextView? = null

    init {
        textTitle = itemView.findViewById(R.id.textTitle)
        textDescription = itemView.findViewById(R.id.textDescription)
        imageAvatar = itemView.findViewById(R.id.imageAvatar)

        textTitle?.setTypeface(textTitle?.getTypeface(), Typeface.BOLD);
    }

    fun bind(movie: Movie) {
        textTitle?.text = movie.title
        textDescription?.text = movie.overview

        val imageUrl = BuildConfig.IMAGE_URL + movie.poster_path

        Picasso.get()
            .load(imageUrl)
            .into(imageAvatar)
    }
}