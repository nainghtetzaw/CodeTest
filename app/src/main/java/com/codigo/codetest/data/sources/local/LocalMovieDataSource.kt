package com.codigo.codetest.data.sources.local

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.*
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getPopularMovies() : Flow<StateFulData<List<Movie>>>
    fun getUpcomingMovies() : Flow<StateFulData<List<Movie>>>
    fun getGenres() : Flow<StateFulData<List<Genre>>>
    fun getMovieById(movieId : Int) : Flow<StateFulData<Movie>>
    fun getGenreById(genreId : Int) : Flow<StateFulData<Genre>>

    suspend fun savePopularMovies(movies : List<Movie>)
    suspend fun saveUpcomingMovies(movies : List<Movie>)
    suspend fun saveMovie(movie : Movie)
    suspend fun saveGenres(genres : List<Genre>)

    suspend fun setFavMovie(movieId: Int, isFavMovie: Boolean)
}