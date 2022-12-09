package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation

class CharacterListAdapter : PagingDataAdapter<CharacterPresentation, CharacterListViewHolder>(
    CharactersDiffCallback()
) {

    var onCharacterItem: ((CharacterPresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false))

    override fun onBindViewHolder(holderContacts: CharacterListViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onCharacterItem?.invoke(getItem(position)!!)
        }
    }

    private class CharactersDiffCallback : DiffUtil.ItemCallback<CharacterPresentation>() {

        override fun areItemsTheSame(
            oldItem: CharacterPresentation,
            newItem: CharacterPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterPresentation,
            newItem: CharacterPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}