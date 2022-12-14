package roman.bannikov.aston_rick_and_morty.domain.repositories.location

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain

interface LocationDetailsRepository {

    suspend fun getLocationById(id: Int): LocationDomain
}