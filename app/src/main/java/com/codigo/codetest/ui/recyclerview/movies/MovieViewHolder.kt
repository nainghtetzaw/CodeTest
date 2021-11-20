package com.codigo.codetest.ui.recyclerview.movies

import com.codigo.codetest.databinding.ItemMovieBinding
import com.codigo.codetest.ui.recyclerview.BaseViewHolder
import com.codigo.codetest.ui.screens.MovieViewObject

class MovieViewHolder(private val binding : ItemMovieBinding, mDelegate : MovieDelegate) : BaseViewHolder<MovieViewObject>(binding.root) {

    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapMovie(it.id)
            }
        }
        binding.ivFav.setOnClickListener {
            mData?.let {
                mDelegate.onTapFav(it.id, !it.isFavMovie)
            }
        }
    }

    override fun setData(data: MovieViewObject) {
        binding.data = data
    }
}