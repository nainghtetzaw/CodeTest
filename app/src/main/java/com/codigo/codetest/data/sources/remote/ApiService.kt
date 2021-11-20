package com.codigo.codetest.data.sources.remote

import com.codigo.codetest.data.models.domain.CastDataResponse
import com.codigo.codetest.data.models.domain.GenreDataResponse
import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.data.models.domain.MovieDataResponse
import com.codigo.codetest.data.sources.local.room.daos.CastDataResponseDao
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/movie/popular")
    fun getPopularMovies() : Flow<MovieDataResponse>

    @GET("/movie/upcoming")
    fun getUpcomingMovies() : Flow<MovieDataResponse>

    @GET("/movie/{movieId}")
    fun getMovieDetail(@Path("movieId") movieId : Int) : Flow<Movie>

    @GET("/genre/movie/list")
    fun getGenres() : Flow<GenreDataResponse>

    @GET("/movie/{movieId}/credits")
    fun getCastsByMovieId(@Path("movieId") movieId: Int) : Flow<CastDataResponse>

}