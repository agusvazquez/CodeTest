package com.scholly.myapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.R
import com.scholly.myapplication.adapter.MovieListAdapter
import com.scholly.myapplication.model.Movie
import com.scholly.myapplication.model.Page
import com.scholly.myapplication.network.services.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val movieService by lazy {
        MovieService.create()
    }
    var disposable: Disposable? = null

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
        loadData()
    }

    private fun loadData() {
        disposable =
            movieService.getPopularMovies(BuildConfig.MOVIEDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(result) },
                    { error -> showError(error.message) }
                )
    }

    private fun showResult(result: Page?) {
        Log.e("TEST", result.toString())
    }

    private fun showError(error: String?) {

    }

    private fun initRecyclerView() {

        val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = MovieListAdapter(mNicolasCageMovies)
            recyclerView.setAdapter(adapter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
