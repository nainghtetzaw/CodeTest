package com.codigo.codetest.data.sources.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntListTypeConverter {

    @TypeConverter
    fun fromJsonToIntList(str : String) : List<Int> {
        val strType = object : TypeToken<List<Int>>(){}.type
        return Gson().fromJson(str, strType)
    }

    @TypeConverter
    fun fromIntListToJson(list : List<Int>) : String {
        return Gson().toJson(list)
    }

}