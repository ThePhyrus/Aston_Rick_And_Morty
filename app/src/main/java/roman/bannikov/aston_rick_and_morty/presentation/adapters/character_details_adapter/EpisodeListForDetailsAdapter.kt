package roman.bannikov.aston_rick_and_morty.presentation.adapters.character_details_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class EpisodeListForDetailsAdapter(
) : ListAdapter<EpisodePresentation, EpisodeListForDetailsViewHolder>(CharacterDetailsDiffCallback()) {

    var onEpisodeItem: ((EpisodePresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeListForDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_episodes_in_characters, parent, false)
        )

    override fun onBindViewHolder(holderContacts: EpisodeListForDetailsViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onEpisodeItem?.invoke(getItem(position)!!)
        }
    }

    private class CharacterDetailsDiffCallback : DiffUtil.ItemCallback<EpisodePresentation>() {

        override fun areItemsTheSame(
            oldItem: EpisodePresentation,
            newItem: EpisodePresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EpisodePresentation,
            newItem: EpisodePresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}