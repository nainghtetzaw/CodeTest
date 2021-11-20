package com.codigo.codetest.data.models.domain

import java.io.Serializable

data class CastDataResponse(
    var id : Int,
    var cast : List<Cast>
) : Serializable
