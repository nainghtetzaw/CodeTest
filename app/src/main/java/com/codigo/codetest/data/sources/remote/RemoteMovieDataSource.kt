package com.codigo.codetest.data.sources.remote

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.Genre
import com.codigo.codetest.data.models.domain.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieDataSource {
    suspend fun getPopularMovies() : Flow<StateFulData<List<Movie>>>
    suspend fun getUpcomingMovies() : Flow<StateFulData<List<Movie>>>
    suspend fun getMovieDetail(movieId : Int) : Flow<StateFulData<Movie>>
    suspend fun getGenres() : Flow<StateFulData<List<Genre>>>
}