package com.scholly.myapplication.network.services

import com.scholly.myapplication.BuildConfig
import com.scholly.myapplication.model.Movie
import com.scholly.myapplication.model.Page
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String):
            Observable<Page>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: String, @Query("api_key") apiKey: String):
            Observable<Movie>
}