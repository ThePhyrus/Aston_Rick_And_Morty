package roman.bannikov.aston_rick_and_morty.data.storage.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import roman.bannikov.aston_rick_and_morty.data.models.character.OriginLocation


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
    fun locationToString(location: OriginLocation): String {
        return Gson().toJson(location)
    }

    @JvmStatic
    @TypeConverter
    fun stringToLocation(json: String): OriginLocation {
        val listType = object : TypeToken<OriginLocation>() {}.type
        return Gson().fromJson(json, listType)
    }
}