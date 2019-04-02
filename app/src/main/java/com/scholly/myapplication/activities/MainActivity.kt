package com.scholly.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.adapter.MovieListAdapter
import com.scholly.myapplication.listeners.RecyclerViewClickListener
import com.scholly.myapplication.model.Movie
import com.scholly.myapplication.model.Page
import com.scholly.myapplication.network.services.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ActivityOptionsCompat
import android.widget.ImageView
import com.scholly.myapplication.R


class MainActivity : AppCompatActivity() {

    val movieService by lazy {
        MovieService.create()
    }
    private var disposable: Disposable? = null

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
                    { error -> showError(error) }
                )
    }

    private fun showResult(result: Page<Movie>?) {
        recyclerView.apply {
            val movieListAdapter = recyclerView.adapter as MovieListAdapter
            movieListAdapter.addAll(result!!.results)
        }
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()
    }

    private fun initRecyclerView() {

        val recyclerView = this.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = MovieListAdapter(ArrayList<Movie>(), object : RecyclerViewClickListener {
                override fun recyclerViewListClicked(
                    v: View,
                    movie: Movie,
                    imageAvatar: ImageView?
                ) {
                    val i = Intent(this@MainActivity, DetailsActivity::class.java)
                    i.putExtra("movie", movie)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@MainActivity,
                        imageAvatar!!,
                        "image")
                    startActivity(i, options.toBundle())
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}