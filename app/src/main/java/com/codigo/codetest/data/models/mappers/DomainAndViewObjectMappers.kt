package com.codigo.codetest.data.models.mappers

import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.ui.screens.MovieViewObject

fun Movie.toMovieViewObject() = MovieViewObject(
    id = id,
    imageUrl = poster_path,
    title = original_title,
    overview = overview,
    isFavMovie = isFavMovie,
    genres = genres?.map { it.name } ?: emptyList()
)
