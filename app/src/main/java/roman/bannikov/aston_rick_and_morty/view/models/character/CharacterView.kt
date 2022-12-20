package roman.bannikov.aston_rick_and_morty.view.models.character


data class CharacterView(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originLocation: Map<String, String>,
    val lastLocation: Map<String, String>,
    val imageUrl: String,
    val episodeIds: List<Int>
)