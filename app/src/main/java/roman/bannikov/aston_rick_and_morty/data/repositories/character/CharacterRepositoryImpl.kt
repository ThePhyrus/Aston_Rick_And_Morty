package roman.bannikov.aston_rick_and_morty.data.repositories.character

import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import roman.bannikov.aston_rick_and_morty.data.mapper.CharacterDataToCharacterDomain
import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData
import roman.bannikov.aston_rick_and_morty.data.paging.CharacterRemoteMediator
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharactersRepository
import java.io.IOException


@ExperimentalPagingApi
class CharacterRepositoryImpl(
    private val characterDetailsApi: CharacterDetailsApi,
    private val characterApi: CharacterApi,
    private val db: RickAndMortyDatabase
) : CharactersRepository {

    override fun getAllCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>> {

        val pagingSourceFactory =
            {
                db.getCharacterDao().getFilteredCharacters(
                    name = name,
                    status = status,
                    gender = gender,
                    type = type,
                    species = species
                )
            }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = CharacterRemoteMediator(
                characterApi = characterApi,
                db = db,
                name = name,
                status = status,
                gender = gender,
                type = type,
                species = species
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it ->
                CharacterDataToCharacterDomain().transform(it)
            }
        }
    }

    override suspend fun getAllCharactersByIds(ids: List<Int>): List<CharacterDomain> =
        withContext(Dispatchers.IO) {
            try {
                if (ids.size > 1) {
                    val idsString: String = ids.joinToString(separator = ",")
                    val characterDataFromApi: Response<List<CharacterData>> =
                        characterApi.getCharactersByIds(ids = idsString)
                    if (characterDataFromApi.isSuccessful) {
                        characterDataFromApi.body()
                            ?.let { db.getCharacterDao().insertAllCharacters(characters = it) }
                    }
                }
                if (ids.size == 1) {
                    val characterFromApi: Response<CharacterData> =
                        characterDetailsApi.getCharacterById(id = ids[0])
                    if (characterFromApi.isSuccessful) {
                        characterFromApi.body()
                            ?.let { db.getCharacterDao().insertCharacter(character = it) }
                    }
                }

            } catch (e: HttpException) {
                Log.e("Log", "${e.code()}")
            } catch (e: IOException) {
                Log.e("Log", "${e.message}")
            }

            return@withContext db.getCharacterDao().getCharactersByIds(ids = ids).map {
                CharacterDataToCharacterDomain().transform(it)
            }
        }
}
