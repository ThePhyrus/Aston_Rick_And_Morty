package roman.bannikov.aston_rick_and_morty.view.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterDetailsBinding
import roman.bannikov.aston_rick_and_morty.utils.Constants
import roman.bannikov.aston_rick_and_morty.utils.factory
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.viewmodels.CharacterDetailsViewModel

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding: FragmentCharacterDetailsBinding get() = _binding!!
    private val viewModel: CharacterDetailsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Сообщим вью-модели, детали какого персонажа надо загрузить/отобразить:
        viewModel.showCharacterDetails(requireArguments().getInt(ARG_CHARACTER_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner, Observer {
            binding.tvCharacterNameDetails.text = it.character.characterName
            if (it.character.characterImage.isNotBlank()) {
                Glide.with(this)
                    .load(it.character.characterImage)
                    .apply(
                        RequestOptions().override(
                            Constants.IMAGE_FIXED_WIDTH,
                            Constants.IMAGE_FIXED_HEIGHT
                        )
                    )
                    .circleCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(binding.ivCharacterImageDetails)
            } else { //обязательно (см CharactersMainFragment())
                Glide.with(this)
                    .load(R.drawable.ic_placeholder)
                    .into(binding.ivCharacterImageDetails)
            }
        })

        binding.btnBack.setOnClickListener {
            navigator().showToast(R.string.moved_back)
            navigator().goBack()
        }
        //todo повесить остальные слушатели нажатий (там tv кликабельные)

        return binding.root
    }


    companion object {
        //Так как фрагмент будет принимать параметры, то опишем их в newInstance()
        private const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"

        @JvmStatic
        fun newInstance(characterId: Int): CharacterDetailsFragment {
            val fragment = CharacterDetailsFragment()
            fragment.arguments = bundleOf(ARG_CHARACTER_ID to characterId)
            return fragment
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}