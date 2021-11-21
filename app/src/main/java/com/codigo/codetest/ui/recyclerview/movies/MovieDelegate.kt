package com.codigo.codetest.ui.recyclerview.movies

import com.codigo.codetest.ui.screens.MovieViewObject

interface MovieDelegate {
    fun onTapMovie(movieId : Int, isFav : Boolean)
    fun onTapFav(movie : MovieViewObject)
}