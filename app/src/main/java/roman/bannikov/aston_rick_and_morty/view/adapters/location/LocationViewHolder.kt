package roman.bannikov.aston_rick_and_morty.view.adapters.location

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import roman.bannikov.aston_rick_and_morty.databinding.ItemLocationBinding
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation


class LocationViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemLocationBinding.bind(itemView)

    fun bind(item: LocationPresentation) = with(binding) {

        tvLocationName.text = item.name
        tvLocationDimension.text = item.dimension
        tvLocationType.text = item.type
    }
}