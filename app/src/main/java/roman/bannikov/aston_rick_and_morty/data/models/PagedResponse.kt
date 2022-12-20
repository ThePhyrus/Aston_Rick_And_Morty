package roman.bannikov.aston_rick_and_morty.data.models

import com.google.gson.annotations.SerializedName

data class PagedResponse<T>(
    @SerializedName("info")
    val info: Info,
    val results: List<T> = listOf()
)