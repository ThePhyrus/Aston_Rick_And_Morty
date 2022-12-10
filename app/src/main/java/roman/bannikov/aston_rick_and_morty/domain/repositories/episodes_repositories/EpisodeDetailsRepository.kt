package roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain

interface EpisodeDetailsRepository {

    suspend fun getEpisodeById(id: Int): EpisodeDomain
}