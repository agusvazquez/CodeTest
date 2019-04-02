package com.scholly.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.model.Movie
import com.scholly.myapplication.viewholders.MovieListViewHolder

class MovieListAdapter  (private val list: ArrayList<Movie>)
    : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

    fun addAll(movies: List<Movie>) {
        for (movie in movies) {
            add(movie)
        }
    }

    private fun add(response: Movie) {
        list.add(response)
        notifyItemInserted(list.size - 1)
    }
}