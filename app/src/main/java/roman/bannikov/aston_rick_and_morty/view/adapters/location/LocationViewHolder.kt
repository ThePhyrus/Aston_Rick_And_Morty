package roman.bannikov.aston_rick_and_morty.view.adapters.location

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.aston_rick_and_morty.databinding.ItemLocationBinding
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView


class LocationViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemLocationBinding.bind(itemView)

    fun bind(item: LocationView) = with(binding) {

        tvLocationName.text = item.name
        tvLocationDimension.text = item.dimension
        tvLocationType.text = item.type
    }
}