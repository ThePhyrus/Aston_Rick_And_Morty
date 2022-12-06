package roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.data.models.characters.Characters
import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterModel

class CharacterEntityToDomainModel :
    Mapper<Characters, CharacterModel> {

    override fun transform(data: Characters): CharacterModel {

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

        return CharacterModel(
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