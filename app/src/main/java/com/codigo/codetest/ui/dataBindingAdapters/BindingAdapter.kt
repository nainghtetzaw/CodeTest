package com.codigo.codetest.ui.dataBindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.codigo.codetest.R

@BindingAdapter("imageUrl")
fun setImageUrl(view : ImageView, url : String?) {
    url?.let {
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w500${it}").placeholder(R.drawable.empty_image).skipMemoryCache(true).into(view)
    }
}
@BindingAdapter("imageResourceId")
fun setImageSource(view : ImageView, resourceId : Int) {
    Glide.with(view.context).load(resourceId).skipMemoryCache(true).into(view)
}
@BindingAdapter("android:text")
fun setText(view : TextView, list : List<String>?) {
    list?.let {
        if(list.isNotEmpty()){
            val text = it.reduce { acc, s ->
                return@reduce "$s/$acc"
            }
            view.text = text
        }
    }
}