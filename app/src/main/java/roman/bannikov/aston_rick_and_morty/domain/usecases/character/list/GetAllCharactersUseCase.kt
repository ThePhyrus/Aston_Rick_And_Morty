package roman.bannikov.aston_rick_and_morty.domain.usecases.character.list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterRepository

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {

    fun execute(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>> {
        return characterRepository.getAllCharacters(
            name = name,
            status = status,
            gender = gender,
            type = type,
            species = species
        )
    }
}
