package com.codigo.codetest.data.sources.local.room.typeConverters

import androidx.room.TypeConverter
import com.codigo.codetest.data.models.entities.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {

    @TypeConverter
    fun fromJsonToGenreList(str : String) : List<GenreEntity> {
        val strType = object : TypeToken<List<GenreEntity>>(){}.type
        return Gson().fromJson(str, strType)
    }

    @TypeConverter
    fun fromGenreListToJson(genres : List<GenreEntity>) : String {
        return Gson().toJson(genres)
    }
}