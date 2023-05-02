package dev.nhason.nivhasonfinalproject.ui.restaurant

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dev.nhason.nivhasonfinalproject.R
import dev.nhason.nivhasonfinalproject.data.models.placesModel.Places
import dev.nhason.nivhasonfinalproject.databinding.FragmentResturantDetailsBinding

class RestaurantDetailsFragment : Fragment() {

    private var _binding : FragmentResturantDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResturantDetailsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val place = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelable("n",Places::class.java)
            } else {
                requireArguments().getParcelable("n")
            }?:return

            binding.nameOfRestaurant.text = place.name
            binding.ratingD.text = place.rating.toString()
            Picasso.get().load(place.photo)
                .into(binding.imageRestaurant)
            binding.priceLevelD.text = place.priceLevel.toString()
            binding.streetName.text = place.vicinity

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}