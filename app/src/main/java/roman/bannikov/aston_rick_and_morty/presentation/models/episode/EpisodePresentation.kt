package roman.bannikov.aston_rick_and_morty.presentation.models.episode

import kotlinx.android.parcel.Parcelize


data class EpisodePresentation(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val residentsIds: List<Int>
)