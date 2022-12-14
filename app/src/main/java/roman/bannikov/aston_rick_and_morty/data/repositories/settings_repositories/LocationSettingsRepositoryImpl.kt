package roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories

import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.LocationSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.repositories.settings.LocationSettingsRepository

class LocationSettingsRepositoryImpl(
    private val locationSettingsPref: LocationSettingsPref
) : LocationSettingsRepository {

    override suspend fun saveLocationSettings(settingsList: Map<String, List<String>>) {
        locationSettingsPref.save(settingsList = settingsList)
    }

    override suspend fun getLocationSettings(): Map<String, List<String>> {
        return locationSettingsPref.get()
    }
}