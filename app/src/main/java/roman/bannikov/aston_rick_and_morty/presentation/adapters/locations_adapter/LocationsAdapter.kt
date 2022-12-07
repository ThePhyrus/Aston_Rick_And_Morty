package roman.bannikov.aston_rick_and_morty.presentation.adapters.locations_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation

class LocationsAdapter : PagingDataAdapter<LocationPresentation, LocationsViewHolder>(
    LocationsDiffCallback()
) {

    var onLocationItem: ((LocationPresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_locations, parent, false))


    override fun onBindViewHolder(holderContacts: LocationsViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onLocationItem?.invoke(getItem(position)!!)
        }
    }

    private class LocationsDiffCallback : DiffUtil.ItemCallback<LocationPresentation>() {

        override fun areItemsTheSame(
            oldItem: LocationPresentation,
            newItem: LocationPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocationPresentation,
            newItem: LocationPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}