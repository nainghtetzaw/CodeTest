package com.codigo.codetest.hilt

import com.codigo.codetest.data.sources.local.LocalMovieDataSource
import com.codigo.codetest.data.sources.local.LocalMovieDataSourceImpl
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import com.codigo.codetest.data.sources.remote.ApiService
import com.codigo.codetest.data.sources.remote.RemoteMovieDataSource
import com.codigo.codetest.data.sources.remote.RemoteMovieDataSourceImpl
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
        genreDao: GenreDao
    ) : LocalMovieDataSource = LocalMovieDataSourceImpl(movieDao,belongsToTypeDao, genreDao)

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(
        apiService : ApiService
    ) : RemoteMovieDataSource = RemoteMovieDataSourceImpl(apiService)

}