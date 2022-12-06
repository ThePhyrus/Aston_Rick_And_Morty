package roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel

interface EpisodeDetailsRepository {

    suspend fun getEpisodeById(id: Int): EpisodeModel
}