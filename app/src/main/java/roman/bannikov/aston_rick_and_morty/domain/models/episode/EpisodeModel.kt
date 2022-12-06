package roman.bannikov.aston_rick_and_morty.domain.models.episode

data class EpisodeModel(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val residentsIds: List<Int>,
)