package roman.bannikov.aston_rick_and_morty.data.storage.sharedPref

interface SharedPrefStorage {
    suspend fun save(settingsList: Map<String,List<String>>)

    suspend fun get(): Map<String,List<String>>
}