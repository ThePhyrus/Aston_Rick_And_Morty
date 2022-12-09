package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class CharacterDetailsAdapter(
) : ListAdapter<EpisodePresentation, CharacterDetailsViewHolder>(CharacterDetailsDiffCallback()) {

    var onEpisodeItem: ((EpisodePresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_episodes_in_character_details, parent, false)
        )

    override fun onBindViewHolder(holderContacts: CharacterDetailsViewHolder, position: Int) {
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