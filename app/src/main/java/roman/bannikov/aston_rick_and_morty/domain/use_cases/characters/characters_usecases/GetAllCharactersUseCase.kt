package roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharactersRepository

class GetAllCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    fun execute(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>> {
        return charactersRepository.getAllCharacters(
            name = name,
            status = status,
            gender = gender,
            type = type,
            species = species
        )
    }
}