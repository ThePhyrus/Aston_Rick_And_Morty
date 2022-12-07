package roman.bannikov.aston_rick_and_morty.data.repositories.characters_repositories

import android.util.Log
import roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model.CharacterEntityToDomainModel
import roman.bannikov.aston_rick_and_morty.data.models.characters.Characters
import roman.bannikov.aston_rick_and_morty.data.remote.api.chatacters.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharacterDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class CharacterDetailsRepositoryImpl(
    private val characterDetailsApi: CharacterDetailsApi,
    private val db: RickAndMortyDatabase
) : CharacterDetailsRepository {

    override suspend fun getCharacterById(id: Int): CharacterModel = withContext(Dispatchers.IO) {
        try {
            val characterFromApi: Response<Characters> =
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

        return@withContext CharacterEntityToDomainModel().transform(
            db.getCharacterDao().getCharacterById(id = id)
        )
    }

}