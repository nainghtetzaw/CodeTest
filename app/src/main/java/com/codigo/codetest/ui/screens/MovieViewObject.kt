package com.codigo.codetest.ui.screens

data class MovieViewObject(
    var id : Int,
    var imageUrl : String,
    var title : String,
    var overview : String,
    var isFavMovie : Boolean,
    val genres : List<String> = emptyList()
)

data class CastViewObject(
    val id : Int,
    val imageUrl : String
)
