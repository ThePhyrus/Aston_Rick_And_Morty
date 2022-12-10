package roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain

interface CharactersRepository {

    fun getAllCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>>

    suspend fun getAllCharactersByIds(ids: List<Int>): List<CharacterDomain>

}