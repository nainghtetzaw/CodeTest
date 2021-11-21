package com.codigo.codetest.data.models.mappers

import com.codigo.codetest.data.models.domain.*
import com.codigo.codetest.data.models.entities.*

//Entities
fun BelongsToTypeEntity.toBelongsToType() = BelongsToType(type, movies.map { it.toMovie() })
fun MovieEntity.toMovie() = Movie(
    id = id,
    original_title = originalTitle,
    overview = overview,
    poster_path = posterPath,
    genre_ids = genreIds,
    genres = genres?.map { it.toGenre() },
    isFavMovie = isFavMovie)

fun GenreEntity.toGenre() = Genre(id, name)

//Domain
fun MovieDataResponse.toBelongsToTypeEntity(type : String) = BelongsToTypeEntity(type, results.map { it.toMovieEntity() })
fun BelongsToType.toBelongsToTypeEntity() = BelongsToTypeEntity(type, movies.map { it.toMovieEntity() })
fun Movie.toMovieEntity() = MovieEntity(id, original_title, overview, poster_path, genre_ids ?: emptyList(), genres?.map { it.toGenreEntity() }, isFavMovie)
fun Genre.toGenreEntity() = GenreEntity(id, name)