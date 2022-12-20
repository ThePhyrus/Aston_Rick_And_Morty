package roman.bannikov.aston_rick_and_morty.view.mapper

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView

class LocationDomainToLocationView :
    Mapper<LocationDomain, LocationView> {

    override fun transform(data: LocationDomain): LocationView {

        return LocationView(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension,
            residentsIds = data.residentsIds
        )
    }
}