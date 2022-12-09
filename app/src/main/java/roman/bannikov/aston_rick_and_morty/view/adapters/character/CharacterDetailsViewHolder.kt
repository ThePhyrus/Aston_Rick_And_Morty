package roman.bannikov.aston_rick_and_morty.view.adapters.character

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodesInCharacterDetailsBinding

import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class CharacterDetailsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodesInCharacterDetailsBinding.bind(itemView)

    fun bind(item: EpisodePresentation) = with(binding) {
        tvEpisodeCodeInCard.text = item.episode
        tvEpisodeNameInCard.text = item.name
        tvEpisodeAirDate.text = item.air_date
    }
}