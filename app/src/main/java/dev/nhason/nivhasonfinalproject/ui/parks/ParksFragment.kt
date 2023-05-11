package dev.nhason.nivhasonfinalproject.ui.parks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nhason.nivhasonfinalproject.R
import dev.nhason.nivhasonfinalproject.adapter.GApiAdapter
import dev.nhason.nivhasonfinalproject.databinding.FragmentParksBinding
import dev.nhason.nivhasonfinalproject.ui.PlacesViewModel


class ParksFragment : Fragment() {

    private var _binding: FragmentParksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val parksViewModel =

            ViewModelProvider(requireActivity()).get(PlacesViewModel::class.java)

        _binding = FragmentParksBinding.inflate(inflater, container, false)

        parksViewModel.parksLiveData.observe(viewLifecycleOwner) {
            val adapter = GApiAdapter(it){places ->
                parksViewModel.getRestaurantDetails(places)
                findNavController().navigate(
                    R.id.action_navigation_parks_to_placeDetailsFragment)
            }
            binding.gApiRecycler.adapter = adapter
            binding.gApiRecycler.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false)
            if (it.isEmpty()){
                binding.titlePlaces.text = "No parks in this area..."
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}