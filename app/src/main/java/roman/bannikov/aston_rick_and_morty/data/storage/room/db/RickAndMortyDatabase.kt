package roman.bannikov.aston_rick_and_morty.data.storage.room.db

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
import roman.bannikov.aston_rick_and_morty.data.storage.room.converter.Converter
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.CharacterDao
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.EpisodeDao
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.LocationDao
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao.CharactersKeysDao
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao.EpisodesKeysDao
import roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao.LocationsKeysDao

@Database(
    entities = [
        CharacterData::class,
        LocationData::class,
        EpisodeData::class,
        CharacterPages::class,
        LocationPages::class,
        EpisodePages::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getEpisodeDao(): EpisodeDao
    abstract fun getCharactersKeyDao(): CharactersKeysDao
    abstract fun getLocationsKeyDao(): LocationsKeysDao
    abstract fun getEpisodesKeyDao(): EpisodesKeysDao


    companion object {
        @Volatile
        private var instance: RickAndMortyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context = context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RickAndMortyDatabase::class.java,
                "RickAndMortyDB.bd"
            ).build()
    }
}