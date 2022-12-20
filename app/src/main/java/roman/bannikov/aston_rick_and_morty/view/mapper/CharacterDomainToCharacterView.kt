package roman.bannikov.aston_rick_and_morty.view.mapper

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView

class CharacterDomainToCharacterView :
    Mapper<CharacterDomain, CharacterView> {

    override fun transform(data: CharacterDomain): CharacterView {

        return CharacterView(
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