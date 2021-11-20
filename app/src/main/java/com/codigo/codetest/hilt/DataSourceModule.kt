package com.codigo.codetest.hilt

import com.codigo.codetest.data.sources.local.LocalMovieDataSource
import com.codigo.codetest.data.sources.local.LocalMovieDataSourceImpl
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.CastDataResponseDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalMovieDataSource(
        movieDao : MovieDao,
        belongsToTypeDao: BelongsToTypeDao,
        castDataResponseDao: CastDataResponseDao,
        genreDao: GenreDao
    ) : LocalMovieDataSource = LocalMovieDataSourceImpl(movieDao,belongsToTypeDao, castDataResponseDao, genreDao)

}