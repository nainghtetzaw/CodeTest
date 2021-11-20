package com.codigo.codetest.ui.recyclerview.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codigo.codetest.databinding.ItemGenreBinding
import com.codigo.codetest.ui.recyclerview.BaseAdapter

class GenreAdapter : BaseAdapter<GenreViewHolder, String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding)
    }
}