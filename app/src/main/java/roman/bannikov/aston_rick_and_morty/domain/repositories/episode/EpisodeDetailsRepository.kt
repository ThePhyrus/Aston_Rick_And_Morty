package roman.bannikov.aston_rick_and_morty.domain.repositories.episode

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain

interface EpisodeDetailsRepository {

    suspend fun getEpisodeById(id: Int): EpisodeDomain
}