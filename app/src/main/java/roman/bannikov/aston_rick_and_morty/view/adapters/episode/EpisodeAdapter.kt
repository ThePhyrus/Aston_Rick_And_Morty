package roman.bannikov.aston_rick_and_morty.view.adapters.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView

class EpisodeAdapter : PagingDataAdapter<EpisodeView, EpisodeViewHolder>(
    EpisodesDiffCallback()
) {

    var onEpisodeItem: ((EpisodeView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode_in_episode_list, parent, false))


    override fun onBindViewHolder(holderContacts: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onEpisodeItem?.invoke(getItem(position)!!)
        }
    }

    private class EpisodesDiffCallback : DiffUtil.ItemCallback<EpisodeView>() {

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