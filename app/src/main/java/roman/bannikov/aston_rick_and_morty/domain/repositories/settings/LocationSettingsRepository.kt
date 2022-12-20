package roman.bannikov.aston_rick_and_morty.domain.repositories.settings

interface LocationSettingsRepository {
    suspend fun saveLocationSettings(types: Map<String, List<String>>)

    suspend fun getLocationSettings(): Map<String, List<String>>

}