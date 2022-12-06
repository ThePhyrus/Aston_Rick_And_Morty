package roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.data.models.episodes.Episode
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel

class EpisodeEntityToDomainModel :
    Mapper<Episode, EpisodeModel> {

    override fun transform(data: Episode): EpisodeModel {

        val residentsIds: List<Int> = data.characters.mapNotNull { residents ->
            residents.dropWhile { char ->
                !char.isDigit()
            }.toIntOrNull()
        }

        return EpisodeModel(
            id = data.id,
            name = data.name,
            episode = data.episode,
            air_date = data.air_date,
            residentsIds = residentsIds
        )
    }
}