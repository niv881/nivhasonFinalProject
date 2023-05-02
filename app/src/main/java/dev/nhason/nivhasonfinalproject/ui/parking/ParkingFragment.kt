package dev.nhason.nivhasonfinalproject.ui.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nhason.nivhasonfinalproject.adapter.GApiAdapter
import dev.nhason.nivhasonfinalproject.databinding.FragmentParkingBinding
import dev.nhason.nivhasonfinalproject.ui.restaurant.PlacesViewModel

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
            val adapter = GApiAdapter(it){}
            binding.gApiRecycler.adapter = adapter
            binding.gApiRecycler.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false)
            if (it.isEmpty()){
                binding.titlePlaces.text = "no parking spot in this area..."
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}