package roman.bannikov.aston_rick_and_morty.view.adapters.character

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodeInCharacterDetailsBinding

import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation

class CharacterDetailsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodeInCharacterDetailsBinding.bind(itemView)

    fun bind(item: EpisodePresentation) = with(binding) {
        tvEpisodeCodeInItem.text = item.episode
        tvEpisodeNameInItem.text = item.name
        tvEpisodeAirDateInItem.text = item.air_date
    }
}