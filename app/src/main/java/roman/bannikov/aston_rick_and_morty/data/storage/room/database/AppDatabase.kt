package roman.bannikov.aston_rick_and_morty.data.storage.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData
import roman.bannikov.aston_rick_and_morty.data.models.pages.CharacterPages
import roman.bannikov.aston_rick_and_morty.data.models.pages.EpisodePages
import roman.bannikov.aston_rick_and_morty.data.models.pages.LocationPages
import roman.bannikov.aston_rick_and_morty.data.storage.room.Converter
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.*
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.DATABASE_NAME

@Database(
    entities = [
        CharacterData::class,
        EpisodeData::class,
        LocationData::class,
        CharacterPages::class,
        LocationPages::class,
        EpisodePages::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context = context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getEpisodeDao(): EpisodeDao
    abstract fun getCharactersKeyDao(): CharactersKeysDao
    abstract fun getLocationsKeyDao(): LocationsKeysDao
    abstract fun getEpisodesKeyDao(): EpisodesKeysDao
}