package com.codigo.codetest.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Casts")
data class CastEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : Int = 0,

    @SerializedName("profile_path")
    var profilePath : String = ""
)
