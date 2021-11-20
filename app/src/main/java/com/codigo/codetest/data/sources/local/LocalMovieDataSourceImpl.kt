package com.codigo.codetest.data.sources.local

import com.codigo.codetest.constants.MoviesTypes
import com.codigo.codetest.data.models.domain.BelongsToType
import com.codigo.codetest.data.models.domain.CastDataResponse
import com.codigo.codetest.data.models.domain.Genre
import com.codigo.codetest.data.models.domain.Movie
import com.codigo.codetest.data.models.mappers.*
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.CastDataResponseDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val movieDao : MovieDao,
    private val belongsToTypeDao: BelongsToTypeDao,
    private val castDataResponseDao: CastDataResponseDao,
    private val genreDao: GenreDao
) : LocalMovieDataSource {
    override fun getPopularMovies(): Flow<List<Movie>> {
        return belongsToTypeDao.getMoviesByType(MoviesTypes.getMovieType(MoviesTypes.Popular))
            .map { it.movies.map {movies -> movies.toMovie() } }
    }

    override fun getUpcomingMovies(): Flow<List<Movie>> {
        return belongsToTypeDao.getMoviesByType(MoviesTypes.getMovieType(MoviesTypes.UpComing))
            .map { it.movies.map {movie -> movie.toMovie() } }
    }

    override fun getGenres(): Flow<List<Genre>> {
        return genreDao.getGenres()
            .map { it.map {genre -> genre.toGenre() } }
    }

    override fun getMovieById(movieId: Int): Flow<Movie> {
        return movieDao.getMovieById(movieId)
            .map { it.toMovie() }
    }

    override fun getWholeMovieById(movieId: Int): Flow<Movie> {
        return movieDao.getWholeMovieById(movieId)
            .map { it.toMovie() }
    }

    override fun getGenreById(genreId: Int): Flow<Genre> {
        return genreDao.getGenreById(genreId)
            .map { it.toGenre() }
    }

    override suspend fun savePopularMovies(movies: BelongsToType) {
        belongsToTypeDao.insertMoviesWithType(movies.toBelongsToTypeEntity())
    }

    override suspend fun saveUpcomingMovies(movies: BelongsToType) {
        belongsToTypeDao.insertMoviesWithType(movies.toBelongsToTypeEntity())
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun saveGenres(genres: List<Genre>) {
        genreDao.insertGenres(genres.map { it.toGenreEntity() })
    }

    override suspend fun saveCastDataResponse(cast: CastDataResponse) {
        castDataResponseDao.insertCasts(cast.toCastResponseEntity())
    }

    override suspend fun setFavMovie(movieId: Int, isFavMovie: Boolean) {
        movieDao.setFavMovie(movieId, isFavMovie)
    }
}