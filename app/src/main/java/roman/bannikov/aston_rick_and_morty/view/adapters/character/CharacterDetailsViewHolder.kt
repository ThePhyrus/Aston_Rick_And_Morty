package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.aston_rick_and_morty.databinding.ItemEpisodeInCharacterDetailsBinding
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView


class CharacterDetailsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEpisodeInCharacterDetailsBinding.bind(itemView)

    fun bind(item: EpisodeView) = with(binding) {
        tvEpisodeCodeInItem.text = item.episode
        tvEpisodeNameInItem.text = item.name
        tvEpisodeAirDateInItem.text = item.air_date
    }
}