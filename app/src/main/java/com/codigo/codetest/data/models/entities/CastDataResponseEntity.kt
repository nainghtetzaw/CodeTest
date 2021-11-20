package com.codigo.codetest.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CastDataResponse")
data class CastDataResponseEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : Int = 0,

    @SerializedName("cast")
    var cast : List<CastEntity> = listOf()
)
