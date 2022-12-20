package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView

class CharacterDetailsAdapter(
) : ListAdapter<EpisodeView, CharacterDetailsViewHolder>(CharacterDetailsDiffCallback()) {

    var onEpisodeItem: ((EpisodeView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_episode_in_character_details, parent, false)
        )

    override fun onBindViewHolder(holderContacts: CharacterDetailsViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onEpisodeItem?.invoke(getItem(position)!!)
        }
    }

    private class CharacterDetailsDiffCallback : DiffUtil.ItemCallback<EpisodeView>() {

        override fun areItemsTheSame(
            oldItem: EpisodeView,
            newItem: EpisodeView
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EpisodeView,
            newItem: EpisodeView
        ): Boolean {
            return oldItem == newItem
        }
    }
}