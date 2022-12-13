package roman.bannikov.aston_rick_and_morty.view.adapters.episode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodeInEpisodeListBinding
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView


class EpisodeViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodeInEpisodeListBinding.bind(itemView)

    fun bind(item: EpisodeView) = with(binding) {
        tvEpisodeName.text = item.name
        tvAirDate.text = item.air_date
        tvEpisodeCode.text = item.episode
    }
}