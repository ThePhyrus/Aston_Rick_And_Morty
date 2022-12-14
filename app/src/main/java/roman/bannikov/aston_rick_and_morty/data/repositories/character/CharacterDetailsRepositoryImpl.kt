package roman.bannikov.aston_rick_and_morty.data.repositories.character

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import roman.bannikov.aston_rick_and_morty.data.mapper.CharacterDataToCharacterDomain
import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterDetailsRepository
import java.io.IOException


class CharacterDetailsRepositoryImpl(
    private val characterDetailsApi: CharacterDetailsApi,
    private val db: RickAndMortyDatabase
) : CharacterDetailsRepository {

    override suspend fun getCharacterById(id: Int): CharacterDomain = withContext(Dispatchers.IO) {
        try {
            val characterFromApi: Response<CharacterData> =
                characterDetailsApi.getCharacterById(id = id)
            if (characterFromApi.isSuccessful) {
                characterFromApi.body()
                    ?.let { db.getCharacterDao().insertCharacter(character = it) }
            }

        } catch (e: HttpException) {
            Log.e("Log", "${e.code()}")
        } catch (e: IOException) {
            Log.e("Log", "${e.message}")
        }

        return@withContext CharacterDataToCharacterDomain().transform(
            db.getCharacterDao().getCharacterById(id = id)
        )
    }

}