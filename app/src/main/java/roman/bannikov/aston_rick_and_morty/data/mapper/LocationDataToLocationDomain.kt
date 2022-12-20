package roman.bannikov.aston_rick_and_morty.data.mapper

import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain

class LocationDataToLocationDomain :
    Mapper<LocationData, LocationDomain> {

    override fun transform(data: LocationData): LocationDomain {

        val residentsIds: List<Int> = data.residents.mapNotNull { residents ->
            residents.dropWhile { char ->
                !char.isDigit()
            }.toIntOrNull()
        }
        return LocationDomain(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension,
            residentsIds = residentsIds
        )
    }
}