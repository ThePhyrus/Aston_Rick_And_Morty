package roman.bannikov.aston_rick_and_morty.domain.use_cases.settings

import roman.bannikov.aston_rick_and_morty.domain.repositories.settings_repositories.LocationSettingsRepository

class LocationsSettingsUseCases(private val locationSettingsRepository: LocationSettingsRepository) {

    suspend fun saveLocationTypes(types: Map<String, List<String>>) {
        locationSettingsRepository.saveLocationSettings(types = types)
    }

    suspend fun getLocationTypes(): Map<String, List<String>> {
        return locationSettingsRepository.getLocationSettings()
    }
}