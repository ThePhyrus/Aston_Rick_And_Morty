package roman.bannikov.aston_rick_and_morty.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ItemCharactersBinding
import roman.bannikov.aston_rick_and_morty.listeners.OnCharacterCardClickListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.utils.Constants.Companion.IMAGE_FIXED_HEIGHT
import roman.bannikov.aston_rick_and_morty.utils.Constants.Companion.IMAGE_FIXED_WIDTH


class CharactersMainAdapter(
    private val actionListener: OnCharacterCardClickListener
) :
    RecyclerView.Adapter<CharactersMainAdapter.CharacterViewHolder>(), View.OnClickListener {

    var lCharacters: List<CharacterModel> = emptyList()
        set(newValue) {
            val diffCallback = CharactersMainDiffCallback(oldList = field, newList = newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback, true)
            field = newValue
//            notifyDataSetChanged() //fixme DiffUtil переповторяй! //todo delete this stroke
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(view: View) { // переопределён из View.OnClickListener
        val character = view.tag as CharacterModel
        actionListener.launchCharacterDetailsFragment(character)
    }

    class CharacterViewHolder(
        val binding: ItemCharactersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(character: CharacterModel) {
            //todo do-do do-do-do... todo do-do-do :) param-param... bam...bam.
        }

    }


    override fun getItemCount(): Int {
        return lCharacters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharactersBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = lCharacters[position]
        with(holder.binding) {
            holder.itemView.tag = character
            val context = holder.itemView.context

            if (character.characterImage.isNotBlank()) {
                Glide.with(ivCharacterImage.context)
                    .load(character.characterImage)
                    .apply(RequestOptions().override(IMAGE_FIXED_WIDTH, IMAGE_FIXED_HEIGHT))
                    .circleCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(ivCharacterImage)
            } else { //блок else обязателен, иначе в работе rv будут баги (какое-то дублирование?)
                ivCharacterImage.setImageResource(R.drawable.ic_character_default_150x150)
            }
            tvCharacterName.text = character.characterName
            tvCharacterSpecies.text = character.characterSpecies
            tvCharacterGender.text = character.characterGender
            tvCharacterStatus.text = character.characterStatus
        }
    }

}

class CharactersMainDiffCallback(
    private val oldList: List<CharacterModel>,
    private val newList: List<CharacterModel>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter.characterId == newCharacter.characterId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter == newCharacter
    }

}
