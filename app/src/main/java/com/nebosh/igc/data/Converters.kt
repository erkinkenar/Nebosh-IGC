package com.nebosh.igc.data
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLocalizedText(value: LocalizedText?): String? {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toLocalizedText(value: String?): LocalizedText? {
        val gson = Gson()
        val type = object : TypeToken<LocalizedText>() {}.type
        return gson.fromJson(value, type)
    }
}
