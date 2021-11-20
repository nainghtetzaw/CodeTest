package com.codigo.codetest.repositories

import com.codigo.codetest.constants.MoviesTypes
import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.data.sources.local.LocalMovieDataSource
import com.codigo.codetest.data.sources.remote.ApiService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource : LocalMovieDataSource,
    private val remoteDataSource : ApiService
) : MovieRepository {

    @FlowPreview
    override fun getPopularMovies(): Flow<List<Movie>>
    =  localDataSource.getPopularMovies()
        .flatMapMerge { remoteDataSource.getPopularMovies() }
        .onEach { localDataSource.savePopularMovies(it.toBelongsToTypeEntity(MoviesTypes.getMovieType(MoviesTypes.Popular)).toBelongsToType()) }
        .flatMapMerge { localDataSource.getPopularMovies() }


}