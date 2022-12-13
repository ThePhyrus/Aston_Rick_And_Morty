package roman.bannikov.aston_rick_and_morty.view.models.episode


data class EpisodeView(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val residentsIds: List<Int>
)