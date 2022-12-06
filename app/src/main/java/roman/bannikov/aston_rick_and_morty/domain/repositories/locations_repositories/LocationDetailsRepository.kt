package roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationModel

interface LocationDetailsRepository {

    suspend fun getLocationById(id: Int): LocationModel
}