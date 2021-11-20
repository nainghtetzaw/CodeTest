package com.codigo.codetest.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "BelongsToType")
data class BelongsToTypeEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("type")
    var type : String = "",

    @SerializedName("movies")
    var movies : List<MovieEntity> = listOf()
)
