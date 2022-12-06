package roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases

import androidx.paging.PagingData
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    fun execute(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterModel>> {
        return charactersRepository.getAllCharacters(
            name = name,
            status = status,
            gender = gender,
            type = type,
            species = species
        )
    }
}