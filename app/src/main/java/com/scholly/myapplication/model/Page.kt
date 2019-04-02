package com.scholly.myapplication.model

data class Page<T>(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<T>) {
}