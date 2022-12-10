package roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class GetEpisodePresentationModel :
    Mapper<EpisodeDomain, EpisodePresentation> {

    override fun transform(data: EpisodeDomain): EpisodePresentation {

        return EpisodePresentation(
            id = data.id,
            name = data.name,
            episode = data.episode,
            air_date = data.air_date,
            residentsIds = data.residentsIds
        )
    }
}