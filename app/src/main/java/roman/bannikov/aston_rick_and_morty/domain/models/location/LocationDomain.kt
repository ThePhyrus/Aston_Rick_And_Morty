package roman.bannikov.aston_rick_and_morty.domain.models.location

data class LocationDomain(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentsIds: List<Int>
)