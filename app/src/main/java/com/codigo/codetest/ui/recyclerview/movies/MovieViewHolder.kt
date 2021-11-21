package com.codigo.codetest.ui.recyclerview.movies

import android.util.Log
import com.codigo.codetest.databinding.ItemMovieBinding
import com.codigo.codetest.ui.recyclerview.BaseViewHolder
import com.codigo.codetest.ui.screens.MovieViewObject

class MovieViewHolder(private val binding : ItemMovieBinding, mDelegate : MovieDelegate) : BaseViewHolder<MovieViewObject>(binding.root) {

    private var isSelected : Boolean = false

    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapMovie(it.id, isSelected)
            }
        }
        binding.ivFav.setOnClickListener {
            mData?.let {
                isSelected = !isSelected
                binding.isSelected = isSelected
//                mDelegate.onTapFav(it.apply { isFavMovie = isSelected })
            }
        }
    }

    override fun setData(data: MovieViewObject) {
        Log.e("ViewModel FavFlag", data.isFavMovie.toString())
        isSelected = data.isFavMovie
        binding.data = data
        binding.isSelected = isSelected
    }
}