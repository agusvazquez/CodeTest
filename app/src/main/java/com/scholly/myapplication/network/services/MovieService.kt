package com.scholly.myapplication.network.services

import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.model.Movie
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    companion object {
        fun create(): MovieService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }

    @GET("movies/get-popular-movies")
    fun getPopularMovies():
            Observable<Movie>

    @GET("movies/get-movie-details")
    fun getMovieDetails(@Query("movie_id") movieId: String):
            Observable<Movie>
}