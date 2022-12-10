package roman.bannikov.aston_rick_and_morty.data.mapper

import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain

class CharacterDataToCharacterDomain :
    Mapper<CharacterData, CharacterDomain> {

    override fun transform(data: CharacterData): CharacterDomain {

        val originLocation: Map<String, String> = mapOf(
            Pair("location_name", data.origin.name),
            Pair("location_id", data.origin.url.substringAfterLast("/")))

        val lastLocation: Map<String, String> = mapOf(
            Pair("location_name", data.location.name),
            Pair("location_id", data.location.url.substringAfterLast("/")))

        val episodeIds: List<Int> = data.episode.mapNotNull { episodeUrl ->
            episodeUrl.dropWhile { char ->
                !char.isDigit()
            }.toIntOrNull()
        }

        return CharacterDomain(
            id = data.id,
            name = data.name,
            species = data.species,
            status = data.status,
            type = data.type,
            gender = data.gender,
            originLocation = originLocation,
            lastLocation = lastLocation,
            imageUrl = data.image,
            episodeIds = episodeIds
        )
    }
}