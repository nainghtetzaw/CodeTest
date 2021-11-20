package com.codigo.codetest.data.sources.local

import com.codigo.codetest.data.models.domain.BelongsToType
import com.codigo.codetest.data.models.domain.CastDataResponse
import com.codigo.codetest.data.models.domain.Genre
import com.codigo.codetest.data.models.domain.Movie
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getPopularMovies() : Flow<List<Movie>>
    fun getUpcomingMovies() : Flow<List<Movie>>
    fun getGenres() : Flow<List<Genre>>
    fun getMovieById(movieId : Int) : Flow<Movie>
    fun getWholeMovieById(movieId: Int) : Flow<Movie>
    fun getGenreById(genreId : Int) : Flow<Genre>

    suspend fun savePopularMovies(movies : BelongsToType)
    suspend fun saveUpcomingMovies(movies : BelongsToType)
    suspend fun saveMovie(movie : Movie)
    suspend fun saveGenres(genres : List<Genre>)
    suspend fun saveCastDataResponse(cast : CastDataResponse)

    suspend fun setFavMovie(movieId: Int, isFavMovie: Boolean)
}