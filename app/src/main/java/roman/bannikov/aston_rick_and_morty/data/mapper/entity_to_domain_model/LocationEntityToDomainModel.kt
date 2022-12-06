package roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.data.models.location.Location
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationModel

class LocationEntityToDomainModel :
    Mapper<Location, LocationModel> {

    override fun transform(data: Location): LocationModel {

        val residentsIds: List<Int> = data.residents.mapNotNull { residents ->
            residents.dropWhile { char ->
                !char.isDigit()
            }.toIntOrNull()
        }
        return LocationModel(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension,
            residentsIds = residentsIds
        )
    }
}