package roman.bannikov.aston_rick_and_morty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ItemCharactersBinding
import roman.bannikov.aston_rick_and_morty.listeners.OnCharacterCardClickListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModel



class CharactersMainAdapter(
    private val actionListener: OnCharacterCardClickListener
) :
    RecyclerView.Adapter<CharactersMainAdapter.CharacterViewHolder>(), View.OnClickListener {

    var lCharacters: List<CharacterModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(view: View) { // переопределён из View.OnClickListener
        val character = view.tag as CharacterModel
        actionListener.launchCharacterDetailsFragment(character)
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
        val binding = ItemCharactersBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener (this)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = lCharacters[position]
        with(holder.binding) {
            holder.itemView.tag = character
            if (character.characterImage.isNotBlank()) {
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




}