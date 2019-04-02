package com.scholly.myapplication.listeners

import android.view.View
import android.widget.ImageView
import com.scholly.myapplication.model.Movie

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(
        v: View,
        position: Movie,
        imageAvatar: ImageView?
    )
}