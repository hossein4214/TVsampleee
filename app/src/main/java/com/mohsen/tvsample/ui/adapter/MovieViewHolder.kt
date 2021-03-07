package com.mohsen.tvsample.ui.adapter

import androidx.leanback.widget.BaseCardView
import com.mohsen.tvsample.data.remote.model.Solon
import com.mohsen.tvsample.databinding.CardMovieBinding

class MovieViewHolder(
    private val itemBinding: CardMovieBinding
): BaseCardView(itemBinding.root.context){
    fun <T> bind(item: Solon){
        isFocusable = true
        isFocusableInTouchMode = true
    }
}
