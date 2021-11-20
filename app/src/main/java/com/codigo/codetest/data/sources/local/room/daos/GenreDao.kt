package com.codigo.codetest.data.sources.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.codetest.data.models.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres : List<GenreEntity>)

    @Query("SELECT * FROM Genres")
    fun getGenres() : Flow<List<GenreEntity>>

    @Query("SELECT * FROM Genres WHERE id == :id")
    fun getGenreById(id : Int) : Flow<GenreEntity>
}