package com.mohsen.tvsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.mohsen.tvsample.data.remote.model.Solon
import com.mohsen.tvsample.databinding.CardMovieBinding
import com.videostreamshows.data.remote.model.Movie

class MyPresernter(
): Presenter(){

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder =
        ViewHolder(MovieViewHolder(CardMovieBinding.inflate(LayoutInflater.from(parent?.context) , parent , false)))



    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        (viewHolder?.view as MovieViewHolder).bind<Solon>(item as Solon)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}