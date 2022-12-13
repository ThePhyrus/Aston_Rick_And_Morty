package roman.bannikov.aston_rick_and_morty.data.storage.sharedPref

import android.content.Context

private const val SHARED_PREF_NAME = "shared_pref_name"
private const val KEY_EPISODES = "episodes"
private const val EPISODES = "EPISODES"
private const val DEFAULT_VALUE = "S01E01"

class EpisodeSettingsPref(context: Context) : SharedPrefStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override suspend fun save(episodes: Map<String, List<String>>) {
        val episodes = episodes.getValue(KEY_EPISODES).joinToString(separator = "\n")
        sharedPreferences.edit().putString(EPISODES, episodes).apply()
    }

    override suspend fun get(): Map<String, List<String>> {
        val episodes: List<String> =
            (sharedPreferences.getString(EPISODES, DEFAULT_VALUE) ?: DEFAULT_VALUE).lines()
        return mapOf(
            KEY_EPISODES to episodes
        )
    }
}