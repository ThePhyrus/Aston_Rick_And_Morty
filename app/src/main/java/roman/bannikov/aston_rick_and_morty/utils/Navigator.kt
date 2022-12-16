package roman.bannikov.aston_rick_and_morty.utils

import androidx.fragment.app.Fragment


interface Navigator {

    fun goBack()

    //character
    fun launchCharacterListFragment()
    fun launchCharacterFilterFragment()
    fun launchCharacterDetailsFragment(characterId: Int)
    fun launchCharacterListFragmentWithArguments(
        status: String?,
        gender: String?,
        species: String?,
        type: String?
    )

    //episode
    fun launchEpisodeListFragment()
    fun launchEpisodeFilterFragment()
    fun launchEpisodeDetailsFragment(episodeId: Int)
    fun launchEpisodeListFragmentWithArguments(episode: String?)

    //location
    fun launchLocationListFragment()
    fun launchLocationFilterFragment()
    fun launchLocationDetailsFragment(locationId: Int)
    fun launchLocationListFragmentWithArguments(type: String?, dimension: String?)

}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}