package roman.bannikov.aston_rick_and_morty.view.adapters.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import roman.bannikov.aston_rick_and_morty.R

import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView

class CharacterListAdapter : PagingDataAdapter<CharacterView, CharacterListViewHolder>(
    CharactersDiffCallback()
) {

    var onCharacterItem: ((CharacterView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))

    override fun onBindViewHolder(holderContacts: CharacterListViewHolder, position: Int) {
        getItem(position)?.let { holderContacts.bind(it) }
        holderContacts.itemView.setOnClickListener {
            onCharacterItem?.invoke(getItem(position)!!)
        }
    }

    private class CharactersDiffCallback : DiffUtil.ItemCallback<CharacterView>() {

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