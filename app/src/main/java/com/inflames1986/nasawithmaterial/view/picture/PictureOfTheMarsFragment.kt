package com.inflames1986.nasawithmaterial.view.picture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.inflames1986.nasawithmaterial.databinding.FragmentPictureOfTheMarsBinding
import com.inflames1986.nasawithmaterial.view.MainActivity
import com.inflames1986.nasawithmaterial.viewmodel.AppState
import com.inflames1986.nasawithmaterial.viewmodel.OneBigFatViewModel
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheMarsFragment : Fragment() {

    private var _binding: FragmentPictureOfTheMarsBinding? = null
    private val binding: FragmentPictureOfTheMarsBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheMarsBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel: OneBigFatViewModel by lazy {
        ViewModelProvider(this).get(OneBigFatViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { render(it) }
        viewModel.getMarsPicture()

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)


    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }


    private fun render(appState: AppState) {
        when (appState) {
            is AppState.Error ->
                Snackbar.make(binding.root, appState.error.toString(), Snackbar.LENGTH_SHORT).show()
            is AppState.Loading -> {}

            is AppState.SuccessMars -> {
                if (appState.serverResponseData.photos.isEmpty()) {
                    Snackbar.make(
                        binding.root,
                        "В этот день curiosity не сделал ни одного снимка",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    val url = appState.serverResponseData.photos.first().imgSrc
                    binding.marsImageView.load(url)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheMarsFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}