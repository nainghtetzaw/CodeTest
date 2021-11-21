package com.codigo.codetest.data.sources.remote

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.Genre
import com.codigo.codetest.data.models.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteMovieDataSource{
    override suspend fun getPopularMovies(): Flow<StateFulData<List<Movie>>> = flow {
        emit(StateFulData.Loading)
        val response = apiService.getPopularMovies()
        try {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(StateFulData.Success(it.results)) }
            }else {
                emit(StateFulData.Error("${response.code()} : ${response.message()}"))
            }
        }catch (e : Exception) {
            emit(StateFulData.Error(e.localizedMessage ?: ""))
        }
    }

    override suspend fun getUpcomingMovies(): Flow<StateFulData<List<Movie>>> = flow {
        emit(StateFulData.Loading)
        val response = apiService.getUpcomingMovies()
        try {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(StateFulData.Success(it.results)) }
            }else {
                emit(StateFulData.Error("${response.code()} : ${response.message()}"))
            }
        }catch (e : Exception) {
            emit(StateFulData.Error(e.localizedMessage ?: ""))
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<StateFulData<Movie>> = flow {
        emit(StateFulData.Loading)
        val response = apiService.getMovieDetail(movieId)
        try {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(StateFulData.Success(it)) }
            }else {
                emit(StateFulData.Error("${response.code()} : ${response.message()}"))
            }
        }catch (e : Exception) {
            emit(StateFulData.Error(e.localizedMessage ?: ""))
        }
    }

    override suspend fun getGenres(): Flow<StateFulData<List<Genre>>> = flow {
        emit(StateFulData.Loading)
        val response = apiService.getGenres()
        try {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { emit(StateFulData.Success(it.genres)) }
            }else {
                emit(StateFulData.Error("${response.code()} : ${response.message()}"))
            }
        }catch (e : Exception) {
            emit(StateFulData.Error(e.localizedMessage ?: ""))
        }
    }
}