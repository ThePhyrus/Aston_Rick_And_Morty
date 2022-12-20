package roman.bannikov.aston_rick_and_morty.domain.repositories.settings

interface EpisodeSettingsRepository {

    suspend fun saveEpisodes(episodes: Map<String, List<String>>)

    suspend fun getEpisodes(): Map<String, List<String>>
}