package com.codigo.codetest.data.sources.local.room.typeConverters

import androidx.room.TypeConverter
import com.codigo.codetest.data.models.entities.CastEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CastListTypeConverter {

    @TypeConverter
    fun fromJsonToCastList(str : String) : List<CastEntity> {
        val strType = object : TypeToken<List<CastEntity>>(){}.type
        return Gson().fromJson(str, strType)
    }

    @TypeConverter
    fun fromCastListToJson(casts : List<CastEntity>) : String {
        return Gson().toJson(casts)
    }

}