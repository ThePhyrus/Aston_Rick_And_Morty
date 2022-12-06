package roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation

import roman.bannikov.aston_rick_and_morty.data.mapper.Mapper
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation

class GetLocationPresentationModel :
    Mapper<LocationModel, LocationPresentation> {

    override fun transform(data: LocationModel): LocationPresentation {

        return LocationPresentation(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension,
            residentsIds = data.residentsIds
        )
    }
}