package dev.nhason.nivhasonfinalproject.ui.parking

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
import dev.nhason.nivhasonfinalproject.databinding.FragmentParkingBinding
import dev.nhason.nivhasonfinalproject.ui.PlacesViewModel

class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val parkingViewModel = ViewModelProvider(requireActivity())[PlacesViewModel::class.java]

        _binding = FragmentParkingBinding.inflate(inflater, container, false)

        parkingViewModel.parkingLiveData.observe(viewLifecycleOwner) {
            val adapter = GApiAdapter(it){places ->
                parkingViewModel.getRestaurantDetails(places)
                findNavController().navigate(
                    R.id.action_nav_parking_to_placeDetailsFragment)
            }
            binding.gApiRecycler.adapter = adapter
            binding.gApiRecycler.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false)
            if (it.isEmpty()){
                binding.titlePlaces.text = "No parking spot in this area..."
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}