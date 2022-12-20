package roman.bannikov.aston_rick_and_morty.view.models.location

data class LocationView(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentsIds: List<Int>
)