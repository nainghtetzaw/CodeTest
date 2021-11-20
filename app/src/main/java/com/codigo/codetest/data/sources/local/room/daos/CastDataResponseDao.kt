package com.codigo.codetest.data.sources.local.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.codetest.data.models.entities.CastEntity
import com.codigo.codetest.data.models.entities.CastDataResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CastDataResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCasts(casts : CastDataResponseEntity)

    @Query("SELECT * FROM CastDataResponse WHERE id == :id")
    fun getCastsById(id : Int) : Flow<CastDataResponseEntity>

}