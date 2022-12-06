package roman.bannikov.aston_rick_and_morty.presentation.adapters.episodes_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.R
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class EpisodesAdapter : PagingDataAdapter<EpisodePresentation, EpisodesViewHolder>(
    EpisodesDiffCallback()
) {

    var onEpisodeItem: ((EpisodePresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_episodes, parent, false))


    override fun onBindViewHolder(holderContacts: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onEpisodeItem?.invoke(getItem(position)!!)
        }
    }

    private class EpisodesDiffCallback : DiffUtil.ItemCallback<EpisodePresentation>() {

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