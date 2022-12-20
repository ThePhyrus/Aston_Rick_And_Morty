package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ItemCharacterBinding
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView


class CharacterListForDetailsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCharacterBinding.bind(itemView)

    fun bind(item: CharacterView) = with(binding) {
        tvCharacterNameInItemCharacter.text = item.name
        tvCharacterSpeciesInItemCharacter.text = item.species
        tvCharacterStatusInItemCharacter.text = item.status
        tvCharacterGenderInItemCharacter.text = item.gender

        Glide.with(itemView)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_loading_placeholder)
            .error(R.drawable.ic_loading_error)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(ivCharacterImageInItemCharacter)
    }
}