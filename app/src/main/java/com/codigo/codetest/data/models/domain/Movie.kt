package com.codigo.codetest.data.models.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("id")
    var id : Int,
    @SerializedName("original_title")
    var original_title : String,
    @SerializedName("overview")
    var overview : String,
    @SerializedName("poster_path")
    var poster_path : String,
    @SerializedName("genre_ids")
    var genre_ids : List<Int>?,
    @SerializedName("genres")
    var genres : List<Genre>?,
    @SerializedName("isFavMovie")
    var isFavMovie : Boolean
) {
    companion object {
        fun empty() : Movie = Movie(0,"","","", listOf(), listOf(),false)
    }
}
