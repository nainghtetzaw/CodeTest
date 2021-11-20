package com.codigo.codetest.hilt

import android.content.Context
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.CastDataResponseDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import com.codigo.codetest.data.sources.local.room.CodeTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataModule {

    @Singleton
    @Provides
    fun provideMovieDao(database : CodeTestDatabase) : MovieDao = database.movieDao()

    @Singleton
    @Provides
    fun provideBelongsToTypeDao(database : CodeTestDatabase) : BelongsToTypeDao = database.belongsToTypeDao()

    @Singleton
    @Provides
    fun provideGenreDao(database: CodeTestDatabase) : GenreDao = database.genreDao()

    @Singleton
    @Provides
    fun provideCastResponseDao(database: CodeTestDatabase) : CastDataResponseDao = database.castResponseDao()

    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : CodeTestDatabase = CodeTestDatabase.getInstance(context)
}