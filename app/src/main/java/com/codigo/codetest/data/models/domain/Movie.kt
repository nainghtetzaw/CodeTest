package com.codigo.codetest.data.models.domain

import java.io.Serializable

data class Movie(
    var id : Int,
    var original_title : String,
    var overview : String,
    var poster_path : String,
    var genre_ids : List<Int>,
    var genres : List<Genre>,
    var isFavMovie : Boolean,
    var casts : CastDataResponse?,
) : Serializable
