package com.codigo.codetest.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Genres")
data class GenreEntity (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : Int = 0,

    @SerializedName("name")
    var name : String = ""
)