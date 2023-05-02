package dev.nhason.nivhasonfinalproject.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import dev.nhason.nivhasonfinalproject.R
import dev.nhason.nivhasonfinalproject.adapter.GApiAdapter
import dev.nhason.nivhasonfinalproject.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment() {

    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val restaurantViewModel =
            ViewModelProvider(requireActivity()).get(PlacesViewModel::class.java)

        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        restaurantViewModel.locationLiveData.observe(viewLifecycleOwner) {
            val adapter = GApiAdapter(it) { places ->
                val bundle = Bundle()
                if (places != null ) {
                    bundle.putParcelable("n", places)
                    findNavController().navigate(
                        R.id.action_navigation_restaurant_to_restaurantDetailsFragment,
                        bundle
                    )
                }else {

                    Toast.makeText(requireContext(), "no View for This Place", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            binding.gApiRecycler.adapter = adapter
            binding.gApiRecycler.layoutManager = LinearLayoutManager(
                context,
                VERTICAL,
                false)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}