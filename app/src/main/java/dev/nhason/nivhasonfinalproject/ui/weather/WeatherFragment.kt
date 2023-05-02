package dev.nhason.nivhasonfinalproject.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dev.nhason.nivhasonfinalproject.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!


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

            binding.textView3.text = it.main.temp.toString()
            binding.textView2.text = it.name

        }

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}