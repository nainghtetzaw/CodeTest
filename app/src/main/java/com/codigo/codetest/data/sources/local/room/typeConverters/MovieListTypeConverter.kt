package com.codigo.codetest.data.sources.local.room.typeConverters

import androidx.room.TypeConverter
import com.codigo.codetest.data.models.entities.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieListTypeConverter {

    @TypeConverter
    fun fromJsonToMovieList(str : String) : List<MovieEntity> {
        val strType = object : TypeToken<List<MovieEntity>>(){}.type
        return Gson().fromJson(str, strType)
    }

    @TypeConverter
    fun fromMovieListToJson(movies : List<MovieEntity>) : String {
        return Gson().toJson(movies)
    }

}