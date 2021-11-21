package com.codigo.codetest.repositories

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies() : Flow<StateFulData<List<Movie>>>
    fun getUpcomingMovies() : Flow<StateFulData<List<Movie>>>
    fun getPopularMoviesFromDatabase() : Flow<List<Movie>>
    fun getUpcomingMoviesFromDatabase() : Flow<List<Movie>>
    fun getMovieDetail(movieId : Int) : Flow<StateFulData<Movie>>

    suspend fun addMovieToFav(movieId : Int, isFavMovie : Boolean)

}