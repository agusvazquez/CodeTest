package com.scholly.myapplication.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.R
import com.scholly.myapplication.model.Movie

class MovieListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.cell_movie_list, parent, false)) {
    private var textTitle: TextView? = null
    private var textImage: TextView? = null
    private var textVoteAverage: TextView? = null

    init {
        textTitle = itemView.findViewById(R.id.textTitle)
        textImage = itemView.findViewById(R.id.textTitle)
        textVoteAverage = itemView.findViewById(R.id.textTitle)
    }

    fun bind(movie: Movie) {
        textTitle?.text = movie.title
    }
}