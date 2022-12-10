package roman.bannikov.aston_rick_and_morty.data.mapper

import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain

class EpisodeDataToEpisodeDomain :
    Mapper<EpisodeData, EpisodeDomain> {

    override fun transform(data: EpisodeData): EpisodeDomain {

        val residentsIds: List<Int> = data.characters.mapNotNull { residents ->
            residents.dropWhile { char ->
                !char.isDigit()
            }.toIntOrNull()
        }

        return EpisodeDomain(
            id = data.id,
            name = data.name,
            episode = data.episode,
            air_date = data.air_date,
            residentsIds = residentsIds
        )
    }
}