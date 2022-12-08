package roman.bannikov.aston_rick_and_morty.presentation.adapters.character_details_adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodesInCharactersBinding

import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class EpisodeListForDetailsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodesInCharactersBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(item: EpisodePresentation) = with(binding) {
        tvEpisodeCodeInCard.text = item.episode
        tvEpisodeNameInCard.text = item.name
        tvEpisodeAirDate.text = item.air_date
    }
}