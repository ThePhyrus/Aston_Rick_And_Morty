package roman.bannikov.aston_rick_and_morty.data.storage.preferences

interface PreferencesStorage {
    suspend fun save(settingsList: Map<String,List<String>>)

    suspend fun get(): Map<String,List<String>>
}