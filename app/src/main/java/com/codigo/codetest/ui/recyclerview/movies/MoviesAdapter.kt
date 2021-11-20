package com.codigo.codetest.ui.recyclerview.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codigo.codetest.databinding.ItemMovieBinding
import com.codigo.codetest.ui.recyclerview.BaseAdapter
import com.codigo.codetest.ui.screens.MovieViewObject

class MoviesAdapter(delegate: MovieDelegate) : BaseAdapter<MovieViewHolder, MovieViewObject>() {

    private val mDelegate : MovieDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding, mDelegate)
    }
}