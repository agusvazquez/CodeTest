package com.scholly.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.R
import com.scholly.myapplication.adapter.MovieListAdapter
import com.scholly.myapplication.model.Movie

class MainActivity : AppCompatActivity() {


    private val mNicolasCageMovies = listOf(
        Movie("Raising Arizona", 1987),
        Movie("Vampire's Kiss", 1988),
        Movie("Con Air", 1997),
        Movie("Gone in 60 Seconds", 1997),
        Movie("National Treasure", 2004),
        Movie("The Wicker Man", 2006),
        Movie("Ghost Rider", 2007),
        Movie("Knowing", 2009)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        //loadData()
    }

    private fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initRecyclerView() {

        val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = MovieListAdapter(mNicolasCageMovies)
            recyclerView.setAdapter(adapter)
        }

    }
}
