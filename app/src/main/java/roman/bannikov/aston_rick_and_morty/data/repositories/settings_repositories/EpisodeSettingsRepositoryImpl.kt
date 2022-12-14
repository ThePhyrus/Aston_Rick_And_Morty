package roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories

import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.EpisodeSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.repositories.settings.EpisodeSettingsRepository

class EpisodeSettingsRepositoryImpl(
    private val episodeSettingsPref: EpisodeSettingsPref
) : EpisodeSettingsRepository {

    override suspend fun saveEpisodes(episodes: Map<String, List<String>>) {
        episodeSettingsPref.save(episodes = episodes)
    }
    override suspend fun getEpisodes(): Map<String, List<String>> {
        return episodeSettingsPref.get()
    }
}