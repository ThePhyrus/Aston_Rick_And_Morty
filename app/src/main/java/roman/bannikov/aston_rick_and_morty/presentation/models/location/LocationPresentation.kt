package roman.bannikov.aston_rick_and_morty.presentation.models.location

import kotlinx.android.parcel.Parcelize

data class LocationPresentation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentsIds: List<Int>
)