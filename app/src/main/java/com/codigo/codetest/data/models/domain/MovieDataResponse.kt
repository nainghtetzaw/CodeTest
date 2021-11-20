package com.codigo.codetest.data.models.domain

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("results")
    var results : List<Movie>
)
