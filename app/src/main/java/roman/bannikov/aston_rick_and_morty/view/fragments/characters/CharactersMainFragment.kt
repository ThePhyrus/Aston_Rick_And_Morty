package roman.bannikov.aston_rick_and_morty.view.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import roman.bannikov.aston_rick_and_morty.App
import roman.bannikov.aston_rick_and_morty.adapters.CharactersMainAdapter

import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharactersMainBinding
import roman.bannikov.aston_rick_and_morty.listeners.OnCharacterCardClickListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModelListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService
import roman.bannikov.aston_rick_and_morty.utils.factory
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.viewmodels.CharactersMainViewModel

class CharactersMainFragment : Fragment() {

    private var _binding: FragmentCharactersMainBinding? = null
    private val binding: FragmentCharactersMainBinding get() = _binding!!
    private lateinit var adapter: CharactersMainAdapter

    //Поле для доступа к вью-модели:
    //Такая запись (и сама фабрика) используется, если во вью-модели конструктор НЕ по умолчанию
    private val viewModel: CharactersMainViewModel by viewModels {factory()}


    private val characterModelService: CharacterModelService
        get() = (context?.applicationContext as App).characterModelService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersMainBinding.inflate(inflater, container, false)
        initAdapter()
        initRV()

        //Подпишемся на данные, к которым любезно предоставляет доступ CharactersMainViewModel.kt
        //в активити вместо viewLifecycleOwner можно передавать просто this (что-то с ЖЦ там)
        viewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            adapter.lCharacters = it
        })
        return binding.root
    }

    private fun initAdapter() {
        adapter = CharactersMainAdapter(object : OnCharacterCardClickListener {
            override fun launchCharacterDetailsFragment(character: CharacterModel) {
                //todo открыть фрагмент с деталями персонажа, тост убрать
                //переходим на экран деталей персонажа
                navigator().showCharacterDetails(character)
                Toast.makeText(context, "${character.characterName}", Toast.LENGTH_SHORT).show()
            }
        })
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        characterModelService.removeListener(charactersListener)
    }
}