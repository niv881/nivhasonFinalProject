package dev.nhason.nivhasonfinalproject.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dev.nhason.nivhasonfinalproject.databinding.FragmentWeatherBinding

const val c = "°C"
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val weatherViewModel =
            ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val root: View = binding.root
        weatherViewModel.locationLiveData.observe(viewLifecycleOwner){
            binding.titleCity.text = it.name
            Picasso.get().load(it.weather[0].iconUrl).into(binding.weatherImage)
            binding.temp.text = "${it.main.temp.toInt()}$c"
            binding.feelsLikeDetails.text = it.main.feelsLike.toInt().toString()
            binding.description.text = it.weather[0].description
        }

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}

