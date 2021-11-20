package com.codigo.codetest.ui.recyclerview.genres

import com.codigo.codetest.databinding.ItemGenreBinding
import com.codigo.codetest.ui.recyclerview.BaseViewHolder

class GenreViewHolder(private val binding : ItemGenreBinding) : BaseViewHolder<String>(binding.root) {
    override fun setData(data: String) {
        binding.genreType = data
    }
}