package roman.bannikov.aston_rick_and_morty.view.mapper

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView

class EpisodeDomainToEpisodeView :
    Mapper<EpisodeDomain, EpisodeView> {

    override fun transform(data: EpisodeDomain): EpisodeView {

        return EpisodeView(
            id = data.id,
            name = data.name,
            episode = data.episode,
            air_date = data.air_date,
            residentsIds = data.residentsIds
        )
    }
}