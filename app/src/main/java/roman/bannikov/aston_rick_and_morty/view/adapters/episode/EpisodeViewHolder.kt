package roman.bannikov.aston_rick_and_morty.view.adapters.episode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodesBinding
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation


class EpisodeViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodesBinding.bind(itemView)

    fun bind(item: EpisodePresentation) = with(binding) {
        tvEpisodeName.text = item.name
        tvAirDate.text = item.air_date
        tvEpisodeCode.text = item.episode
    }
}