package com.scholly.myapplication.activities

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.R
import com.scholly.myapplication.model.Movie
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val movie = this.intent.getSerializableExtra("movie") as Movie
        loadViews(movie)
    }

    private fun loadViews(movie: Movie) {
        val textTitle = this.findViewById<TextView>(R.id.textTitle)
        val textDescription = this.findViewById<TextView>(R.id.textDescription)
        val imageAvatar = this.findViewById<ImageView>(R.id.imageAvatar)

        textTitle.setTypeface(textTitle.getTypeface(), Typeface.BOLD);

        textTitle.text = movie.title
        textDescription.text = movie.overview

        val imageUrl = BuildConfig.IMAGE_URL + movie.poster_path

        Picasso.get()
            .load(imageUrl)
            .into(imageAvatar)
    }
}