package com.codigo.codetest.data.models.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreDataResponse(
    var genres : List<Genre>
) : Serializable
