package com.codigo.codetest.ui.screens

data class MovieViewObject(
    val id : Int,
    val imageUrl : String,
    val title : String,
    val overview : String,
    val isFavMovie : Boolean,
    val genres : List<String> = emptyList()
)

data class CastViewObject(
    val id : Int,
    val imageUrl : String
)
