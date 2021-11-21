package com.codigo.codetest.data.sources.remote

import com.codigo.codetest.constants.API_KEY
import com.codigo.codetest.data.models.domain.GenreDataResponse
import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.data.models.domain.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String = API_KEY) : Response<MovieDataResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey : String = API_KEY) : Response<MovieDataResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId : Int,@Query("api_key") apiKey : String = API_KEY) : Response<Movie>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey : String = API_KEY) : Response<GenreDataResponse>

}