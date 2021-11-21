package com.codigo.codetest.data.models.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : Int = 0,

    @SerializedName("original_title")
    var originalTitle : String = "",

    @SerializedName("overview")
    var overview : String = "",

    @SerializedName("poster_path")
    var posterPath : String = "",

    @SerializedName("genre_ids")
    var genreIds : List<Int> = listOf(),

    @SerializedName("genres")
    var genres : List<GenreEntity>? = listOf(),

    @SerializedName("is_Fav_Movie")
    var isFavMovie : Boolean = false
)
