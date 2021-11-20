package com.codigo.codetest.ui.recyclerview.casts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codigo.codetest.databinding.ItemCastBinding
import com.codigo.codetest.ui.recyclerview.BaseAdapter
import com.codigo.codetest.ui.screens.CastViewObject

class CastAdapter : BaseAdapter<CastViewHolder, CastViewObject>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCastBinding.inflate(layoutInflater, parent, false)
        return CastViewHolder(binding)
    }
}