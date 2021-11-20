package com.codigo.codetest.constants

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "335f40afe5fa89d8fe3a48849b8e41ad"
const val MOVIE_DETAIL = "/movie/"
const val GENRES = "/genre/movie/list"

enum class UrlTypes {
    PopularMovies,
    UpcomingMovies,
    MovieDetail,
    Genres,
    Casts;

    companion object {
        fun getUrl(type : UrlTypes) : String
        = when (type) {
            PopularMovies -> "movie/popular"
            UpcomingMovies -> "movie/upcoming"
            MovieDetail -> "movie/{movieId}"
            Genres -> "genre/movie/list"
            Casts -> "movie/{movieId}/credits"
        }
    }
}

enum class MoviesTypes {
    UpComing,
    Popular;

    companion object {
        fun getMovieType(type : MoviesTypes) : String
        = when (type) {
            UpComing -> "upcoming"
            Popular -> "popular"
        }
    }
}