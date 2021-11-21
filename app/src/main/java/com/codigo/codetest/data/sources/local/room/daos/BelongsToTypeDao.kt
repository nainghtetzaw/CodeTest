package com.codigo.codetest.data.sources.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.codetest.data.models.entities.BelongsToTypeEntity
import com.codigo.codetest.data.models.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BelongsToTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesWithType(type : BelongsToTypeEntity)

    @Query("SELECT * FROM BelongsToType WHERE type == :type")
    fun getMoviesByType(type : String) : Flow<BelongsToTypeEntity?>

}