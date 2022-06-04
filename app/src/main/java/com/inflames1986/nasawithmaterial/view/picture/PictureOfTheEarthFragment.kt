package com.inflames1986.nasawithmaterial.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.FragmentPictureOfTheEarthBinding
import com.inflames1986.nasawithmaterial.viewmodel.PictureOfTheDayAppState
import com.inflames1986.nasawithmaterial.viewmodel.PictureOfTheDayViewModel
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheEarthFragment : Fragment() {


    private var _binding: FragmentPictureOfTheEarthBinding? = null
    private val binding: FragmentPictureOfTheEarthBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendRequest()
    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }


    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Error -> {

            }
            is PictureOfTheDayAppState.Loading -> {
                binding.earthImageView.load(R.drawable.nasa_logo)
            }
            is PictureOfTheDayAppState.Success -> {


                binding.earthImageView.load(pictureOfTheDayAppState.pictureOfTheDayResponseData.url) {
                    crossfade(true)
                    placeholder(R.drawable.nasa_logo).transformations(CircleCropTransformation())
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheEarthFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}