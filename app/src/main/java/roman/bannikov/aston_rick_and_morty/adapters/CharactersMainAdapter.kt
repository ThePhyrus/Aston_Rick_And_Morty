package roman.bannikov.aston_rick_and_morty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ItemCharactersBinding
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.utils.Constants.Companion.RANDOM_IMAGE_URL

class CharactersMainAdapter : RecyclerView.Adapter<CharactersMainAdapter.CharacterViewHolder>() {

    var lCharacters: List<CharacterModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CharacterViewHolder(
        val binding: ItemCharactersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(character: CharacterModel) {
            //todo do do do do do do do
        }

    }




    override fun getItemCount(): Int {
        return lCharacters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharactersBinding.inflate(inflater, parent,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = lCharacters[position]
        with(holder.binding){
            //ivCharacterImage...
            if (character.characterImage.isNotBlank()){
                Glide.with(ivCharacterImage.context)
                    .load(character.characterImage)
                    .circleCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(ivCharacterImage)
            } else {
                ivCharacterImage.setImageResource(R.drawable.ic_character_default_150x150)
            }
            tvCharacterName.text = character.characterName
            tvCharacterSpecies.text = character.characterSpecies
            tvCharacterGender.text = character.characterGender
            tvCharacterStatus.text = character.characterStatus
        }
    }


    /*
var characterId : Int,
var characterImage : String,
var characterName : String,
var characterGender : String,
var characterSpecies : String,
var characterStatus : String
*/

}