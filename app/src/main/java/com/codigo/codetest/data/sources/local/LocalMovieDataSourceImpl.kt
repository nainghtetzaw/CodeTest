package com.codigo.codetest.data.sources.local

import com.codigo.codetest.constants.MoviesTypes
import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.*
import com.codigo.codetest.data.models.mappers.*
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val movieDao : MovieDao,
    private val belongsToTypeDao: BelongsToTypeDao,
    private val genreDao: GenreDao
) : LocalMovieDataSource {

    override fun getPopularMovies(): Flow<StateFulData<List<Movie>>> =
        belongsToTypeDao.getMoviesByType(MoviesTypes.getMovieType(MoviesTypes.Popular))
            .flatMapLatest { entity ->
                val movies = entity?.movies
                flow {
                    emit(StateFulData.Success(listOf()))
                    movies?.let { emit(StateFulData.Success(it.map { movieEntity -> movieEntity.toMovie() })) }
                }
            }

    override fun getUpcomingMovies(): Flow<StateFulData<List<Movie>>> =
        belongsToTypeDao.getMoviesByType(MoviesTypes.getMovieType(MoviesTypes.UpComing))
            .flatMapLatest { entity ->
                val movies = entity?.movies
                flow {
                    emit(StateFulData.Success(listOf()))
                    movies?.let { emit(StateFulData.Success(it.map { movieEntity -> movieEntity.toMovie() })) }
                }
            }

    override fun getGenres(): Flow<StateFulData<List<Genre>>> = genreDao.getGenres()
        .flatMapLatest { entity ->
            flow {
                emit(StateFulData.Success(listOf()))
                entity?.let { emit(StateFulData.Success(it.map { genreEntity -> genreEntity.toGenre() })) }
            }
        }

    override fun getMovieById(movieId: Int): Flow<StateFulData<Movie>> =
        movieDao.getMovieById(movieId)
            .flatMapLatest { flow {
                emit(StateFulData.Success(Movie.empty()))
                it?.let { movie -> emit(StateFulData.Success(movie.toMovie())) }
            }
            }


    override fun getGenreById(genreId: Int): Flow<StateFulData<Genre>>
    = genreDao.getGenreById(genreId)
            .flatMapLatest { flow { emit(StateFulData.Success(it.toGenre())) } }

    override suspend fun savePopularMovies(movies: List<Movie>) {
        belongsToTypeDao.insertMoviesWithType(
            BelongsToType(
                type = MoviesTypes.getMovieType(MoviesTypes.Popular),
                movies =  movies
            ).toBelongsToTypeEntity()
        )
    }

    override suspend fun saveUpcomingMovies(movies: List<Movie>) {
        belongsToTypeDao.insertMoviesWithType(
            BelongsToType(
                type = MoviesTypes.getMovieType(MoviesTypes.UpComing),
                movies =  movies
            ).toBelongsToTypeEntity()
        )
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun saveGenres(genres: List<Genre>) {
        genreDao.insertGenres(genres.map { it.toGenreEntity() })
    }

    override suspend fun setFavMovie(movieId: Int, isFavMovie: Boolean) {
        movieDao.setFavMovie(movieId, isFavMovie)
    }
}