package com.codigo.codetest.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : BaseViewHolder<W>, W>() : RecyclerView.Adapter<T>() {

    private var mData : MutableList<W> = ArrayList()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.mData = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(newData : List<W>){
        mData = newData.toMutableList()
        notifyDataSetChanged()
    }

    fun appendNewData(newData : List<W>){
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    fun addNewData(newData : W) {
        mData.add(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int) : W? {
        return if (position < mData.size) mData[position] else null
    }

    fun getItems() : List<W> {
        return mData
    }

    fun clearData() {
        mData = mutableListOf()
        notifyDataSetChanged()
    }
}