package roman.bannikov.aston_rick_and_morty.view.adapters.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView

class LocationAdapter : PagingDataAdapter<LocationView, LocationViewHolder>(
    LocationsDiffCallback()
) {

    var onLocationItem: ((LocationView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))


    override fun onBindViewHolder(holderContacts: LocationViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onLocationItem?.invoke(getItem(position)!!)
        }
    }

    private class LocationsDiffCallback : DiffUtil.ItemCallback<LocationView>() {

        override fun areItemsTheSame(
            oldItem: LocationView,
            newItem: LocationView
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocationView,
            newItem: LocationView
        ): Boolean {
            return oldItem == newItem
        }
    }
}