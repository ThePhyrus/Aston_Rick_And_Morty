package roman.bannikov.aston_rick_and_morty.data.storage.room.converter

import androidx.room.TypeConverter
import roman.bannikov.aston_rick_and_morty.data.models.characters.LinkedLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converter {
    @JvmStatic
    @TypeConverter
    fun listToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @JvmStatic
    @TypeConverter
    fun stringToList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @JvmStatic
    @TypeConverter
    fun locationToString(location: LinkedLocation): String {
        return Gson().toJson(location)
    }

    @JvmStatic
    @TypeConverter
    fun stringToLocation(json: String): LinkedLocation {
        val listType = object : TypeToken<LinkedLocation>() {}.type
        return Gson().fromJson(json, listType)
    }
}