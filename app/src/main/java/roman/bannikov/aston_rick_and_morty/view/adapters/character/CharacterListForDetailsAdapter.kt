package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView


class CharacterListForDetailsAdapter(
) : ListAdapter<CharacterView, CharacterListForDetailsViewHolder>(
    CharacterDetailsDiffCallback()
) {

    var onCharacterItem: ((CharacterView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterListForDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_character, parent, false)
        )

    override fun onBindViewHolder(holderContacts: CharacterListForDetailsViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onCharacterItem?.invoke(getItem(position)!!)
        }
    }

    private class CharacterDetailsDiffCallback : DiffUtil.ItemCallback<CharacterView>() {

        override fun areItemsTheSame(
            oldItem: CharacterView,
            newItem: CharacterView
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterView,
            newItem: CharacterView
        ): Boolean {
            return oldItem == newItem
        }
    }
}