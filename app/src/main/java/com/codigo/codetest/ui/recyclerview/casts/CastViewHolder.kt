package com.codigo.codetest.ui.recyclerview.casts

import com.codigo.codetest.databinding.ItemCastBinding
import com.codigo.codetest.ui.recyclerview.BaseViewHolder
import com.codigo.codetest.ui.screens.CastViewObject

class CastViewHolder(private val binding : ItemCastBinding) : BaseViewHolder<CastViewObject>(binding.root) {
    override fun setData(data: CastViewObject) {
        binding.imageUrl = data.imageUrl
    }
}