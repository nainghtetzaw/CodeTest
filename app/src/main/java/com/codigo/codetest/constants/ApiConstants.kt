package com.codigo.codetest.constants

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "335f40afe5fa89d8fe3a48849b8e41ad"

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