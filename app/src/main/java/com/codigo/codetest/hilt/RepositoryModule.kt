package com.codigo.codetest.hilt

import com.codigo.codetest.data.sources.local.LocalMovieDataSource
import com.codigo.codetest.data.sources.remote.ApiService
import com.codigo.codetest.repositories.MovieRepository
import com.codigo.codetest.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        localDataSource : LocalMovieDataSource,
        remoteMovieDataSource: ApiService
    ) : MovieRepository = MovieRepositoryImpl(localDataSource, remoteMovieDataSource)

}