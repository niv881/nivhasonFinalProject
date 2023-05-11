package dev.nhason.nivhasonfinalproject.ui.place_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dev.nhason.nivhasonfinalproject.databinding.FragmentPlaceDetailsBinding
import dev.nhason.nivhasonfinalproject.ui.PlacesViewModel

const val MARKET_FOR_WAZE = "market://details?id=com.waze"
class PlaceDetailsFragment : Fragment() {

    private var _binding : FragmentPlaceDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var restaurantDetailsViewModel: PlacesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        restaurantDetailsViewModel =
            ViewModelProvider(requireActivity()).get(PlacesViewModel::class.java)


        _binding = FragmentPlaceDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantDetailsViewModel.restaurantDetailsLiveData.observe(viewLifecycleOwner) { place ->
            binding.nameOfRestaurant.text = place.result.name
            Picasso.get().load(place.result.photo)
                .into(binding.imageRestaurant)
            binding.ratingD.text = place.result.ratingDetails
            binding.streetName.text = place.result.vicinity

            binding.iconGoogleMaps.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(place.result.url))
                startActivity(browserIntent)
            }
            binding.wazeClick.setOnClickListener {
                try {
                    val url =
                        "https://www.waze.com/ul?ll=${place.result.geometry.location.lat}%2C" +
                                "${place.result.geometry.location.lng}&navigate=yes&zoom=17"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_FOR_WAZE))
                    startActivity(intent)
                }
            }
        }
        
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

