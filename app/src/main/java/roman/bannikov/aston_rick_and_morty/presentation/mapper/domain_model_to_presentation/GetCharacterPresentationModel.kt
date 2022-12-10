package roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation

class GetCharacterPresentationModel :
    Mapper<CharacterDomain, CharacterPresentation> {

    override fun transform(data: CharacterDomain): CharacterPresentation {

        return CharacterPresentation(
            id = data.id,
            name = data.name,
            species = data.species,
            status = data.status,
            type = data.type,
            gender = data.gender,
            originLocation = data.originLocation,
            lastLocation = data.lastLocation,
            imageUrl = data.imageUrl,
            episodeIds = data.episodeIds,
        )
    }
}