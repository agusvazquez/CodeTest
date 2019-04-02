package com.scholly.myapplication.network.services

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface MovieService {

    companion object {
        fun create(): MovieService {

            val gson = GsonBuilder()
                .setLenient()
                .create();

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String):
            Observable<Page<Movie>>

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") movieId: String, @Query("api_key") apiKey: String):
            Observable<Movie>
}