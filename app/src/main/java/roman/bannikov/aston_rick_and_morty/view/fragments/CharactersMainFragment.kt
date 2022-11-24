package roman.bannikov.aston_rick_and_morty.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import roman.bannikov.aston_rick_and_morty.App
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.adapters.CharactersMainAdapter

import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharactersMainBinding
import roman.bannikov.aston_rick_and_morty.listeners.OnCharacterCardClickListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModelListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService
import roman.bannikov.aston_rick_and_morty.view.MainActivity

class CharactersMainFragment : Fragment() {

    private var _binding: FragmentCharactersMainBinding? = null
    private val binding: FragmentCharactersMainBinding get() = _binding!!
    private lateinit var adapter: CharactersMainAdapter
//    private val mContext = getApplicationContext()

    private val characterModelService: CharacterModelService
        get() = (context?.applicationContext as App).characterModelService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersMainBinding.inflate(inflater, container, false)

        adapter = CharactersMainAdapter(object : OnCharacterCardClickListener {
            override fun launchCharacterDetailsFragment(character: CharacterModel) {
                //todo открыть фрагмент с деталями персонажа, тост убрать
                Toast.makeText(context, "${character.characterName}", Toast.LENGTH_SHORT).show()
            }

        })

        initRV()

        characterModelService.addListener(charactersListener)

        return binding.root
    }

    private val charactersListener: CharacterModelListener = {
        adapter.lCharacters = it
    }

    private fun initRV() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacters.layoutManager = layoutManager
        binding.rvCharacters.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersMainFragment()
    }

//    private fun initRecyclerView() {
//        with(binding.rvCharacters) {
//            layoutManager = LinearLayoutManager(requireContext())
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            adapter = charactersAdapter
//        }
//        charactersAdapter.onCharacterItem = { navigator().openCharacterDetailFragment(it.id) }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        characterModelService.removeListener(charactersListener)
    }
}