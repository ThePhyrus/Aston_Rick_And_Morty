package roman.bannikov.aston_rick_and_morty.domain.usecases.settings

import roman.bannikov.aston_rick_and_morty.domain.repositories.settings.EpisodeSettingsRepository

class EpisodesSettingsUseCases(private val episodesSettingsRepository: EpisodeSettingsRepository) {

    suspend fun saveEpisodes(episodes: Map<String, List<String>>) {
        episodesSettingsRepository.saveEpisodes(episodes = episodes)
    }

    suspend fun getEpisodes(): Map<String, List<String>> {
        return episodesSettingsRepository.getEpisodes()
    }
}