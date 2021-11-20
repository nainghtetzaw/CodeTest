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
    genres = genres.map { it.toGenre() },
    isFavMovie = isFavMovie,
    casts = null)
fun WholeMovieEntity.toMovie() = Movie(
    id = movie.id,
    original_title = movie.originalTitle,
    overview = movie.overview,
    poster_path = movie.posterPath,
    genre_ids = movie.genreIds,
    genres = movie.genres.map { it.toGenre() },
    isFavMovie = movie.isFavMovie,
    casts = casts?.toCastResponse())
fun GenreEntity.toGenre() = Genre(id, name)
fun CastDataResponseEntity.toCastResponse() = CastDataResponse(id, cast.map { it.toCast() })
fun CastEntity.toCast() = Cast(id, profilePath)

//Domain
fun MovieDataResponse.toBelongsToTypeEntity(type : String) = BelongsToTypeEntity(type, results.map { it.toMovieEntity() })
fun BelongsToType.toBelongsToTypeEntity() = BelongsToTypeEntity(type, movies.map { it.toMovieEntity() })
fun Movie.toMovieEntity() = MovieEntity(id, original_title, overview, poster_path, genre_ids, genres.map { it.toGenreEntity() }, isFavMovie)
fun Genre.toGenreEntity() = GenreEntity(id, name)
fun CastDataResponse.toCastResponseEntity() = CastDataResponseEntity(id, cast.map { it.toCastEntity() })
fun Cast.toCastEntity() = CastEntity(id, profile_path)