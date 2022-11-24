package roman.bannikov.aston_rick_and_morty.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import roman.bannikov.aston_rick_and_morty.R

class LocationsMainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations_main, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LocationsMainFragment()
    }
}