package com.codigo.codetest.ui.recyclerview.movies

interface MovieDelegate {
    fun onTapMovie(movieId : Int)
    fun onTapFav(movieId : Int, isFavMovie : Boolean)
}