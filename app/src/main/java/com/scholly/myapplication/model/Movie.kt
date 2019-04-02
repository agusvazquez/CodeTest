package com.scholly.myapplication.model

import java.io.Serializable

data class Movie(val title: String, val overview: String, val poster_path: String) : Serializable