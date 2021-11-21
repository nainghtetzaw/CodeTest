package com.codigo.codetest.repositories

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.data.sources.local.LocalMovieDataSource
import com.codigo.codetest.data.sources.remote.RemoteMovieDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MovieRepositoryImpl @Inject constructor(
    private val localDataSource : LocalMovieDataSource,
    private val remoteDataSource : RemoteMovieDataSource
) : MovieRepository {

    override fun getPopularMovies(): Flow<StateFulData<List<Movie>>>
    = localDataSource.getPopularMovies()
        .take(1)
        .flatMapMerge { remoteDataSource.getPopularMovies() }
        .catch { emit(StateFulData.Error(it.localizedMessage ?: "")) }
        .onEach { if(it is StateFulData.Success) localDataSource.savePopularMovies(it.result) }
        .flatMapLatest { localDataSource.getPopularMovies() }
        .onStart { StateFulData.Loading }

    override fun getUpcomingMovies(): Flow<StateFulData<List<Movie>>>
    = localDataSource.getUpcomingMovies()
        .take(1)
        .flatMapMerge { remoteDataSource.getUpcomingMovies() }
        .catch { emit(StateFulData.Error(it.localizedMessage ?: "")) }
        .onEach { if(it is StateFulData.Success) localDataSource.saveUpcomingMovies(it.result) }
        .flatMapLatest { localDataSource.getUpcomingMovies() }
        .onStart { emit(StateFulData.Loading) }

    override fun getPopularMoviesFromDatabase(): Flow<List<Movie>>
    = localDataSource.getPopularMovies()
        .take(1)
        .flatMapLatest { flow { if(it is StateFulData.Success) emit(it.result) } }


    override fun getUpcomingMoviesFromDatabase(): Flow<List<Movie>>
    = localDataSource.getUpcomingMovies()
        .take(1)
        .flatMapLatest { flow { if(it is StateFulData.Success) emit(it.result) } }

    override fun getMovieDetail(movieId: Int): Flow<StateFulData<Movie>>
    = localDataSource.getMovieById(movieId)
        .take(1)
        .flatMapMerge { remoteDataSource.getMovieDetail(movieId) }
        .catch { emit(StateFulData.Error(it.localizedMessage ?: "")) }
        .onEach { if(it is StateFulData.Success) localDataSource.saveMovie(it.result) }
        .flatMapLatest { localDataSource.getMovieById(movieId) }
        .onStart { emit(StateFulData.Loading) }

    override suspend fun addMovieToFav(movieId: Int, isFavMovie: Boolean){
        localDataSource.setFavMovie(movieId, isFavMovie)
    }


}