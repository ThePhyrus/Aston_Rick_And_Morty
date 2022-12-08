package roman.bannikov.aston_rick_and_morty.presentation.adapters.characters_adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ItemCharactersBinding

import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation


class CharactersViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCharactersBinding.bind(itemView)

    fun bind(item: CharacterPresentation) = with(binding) {
        tvCharacterNameInItemCharacter.text = item.name
        tvCharacterSpeciesInItemCharacter.text = item.species
        tvCharacterStatusInItemCharacter.text = item.status
        tvCharacterGenderInItemCharacter.text = item.gender

/*        when (item.gender) {
            "Male" -> itemGender.setImageResource(R.drawable.ic_male)
            "Female" -> itemGender.setImageResource(R.drawable.ic_female)
            "Unknown" -> itemGender.setImageResource(R.drawable.ic_unknown)
            else -> itemGender.setImageResource(R.drawable.ic_genderless)
        }*/

        Glide.with(itemView)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_dissconect)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(ivCharacterImageInItemCharacter)
    }
}