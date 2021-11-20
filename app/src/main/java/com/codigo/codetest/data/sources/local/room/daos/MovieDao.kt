package com.codigo.codetest.data.sources.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.codetest.data.models.entities.MovieEntity
import com.codigo.codetest.data.models.entities.WholeMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie : MovieEntity)

    @Query("UPDATE Movies SET isFavMovie = :isFavMovie WHERE id == :id")
    suspend fun setFavMovie(id : Int, isFavMovie : Boolean)

    @Query("SELECT * FROM Movies WHERE id == :id")
    fun getMovieById(id : Int) : Flow<MovieEntity>

    @Query("SELECT * FROM Movies WHERE id == :id")
    fun getWholeMovieById(id : Int) : Flow<WholeMovieEntity>
}