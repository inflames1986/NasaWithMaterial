package com.inflames1986.nasawithmaterial.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.SpannedString
import android.text.style.*
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.FragmentPictureOfTheDayBinding
import com.inflames1986.nasawithmaterial.view.MainActivity
import com.inflames1986.nasawithmaterial.view.settings.SettingsFragment
import com.inflames1986.nasawithmaterial.viewmodel.PictureOfTheDayAppState
import com.inflames1986.nasawithmaterial.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {


    var isMain = true
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.app_bar_fav -> {
                Log.d("@@@", "app_bar_fav")
            }
            R.id.app_bar_settings -> {
                Log.d("@@@", "app_bar_settings")
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .addToBackStack("0")
                    .commit()

            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.lifeHack.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("@@@", "$slideOffset")
            }
        })

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if(isMain){
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_back_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_second_bottom_bar)
            }else{
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(requireContext(),R.drawable.ic_hamburger_menu_bottom_bar))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_plus_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Error -> {

            }
            is PictureOfTheDayAppState.Loading -> {
                binding.imageView.load(R.drawable.nasa_logo)
            }
            is PictureOfTheDayAppState.Success -> {


                binding.imageView.load(pictureOfTheDayAppState.pictureOfTheDayResponseData.url){
                    crossfade(true)
                    placeholder(R.drawable.nasa_logo).transformations(CircleCropTransformation())
                    transformations(CircleCropTransformation())
                }

                binding.lifeHack.title.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.title
                binding.lifeHack.explanation.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.explanation

                val textSpannable = "My text \nbullet one \nbullet two \nbullet three \nbullet four \nbullet five \nbullet six"


                val spannedString: SpannedString
                val spannableString: SpannableString = SpannableString(textSpannable)
                val spannableStringBuilder: SpannableStringBuilder

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {


                    spannableString.setSpan(
                        BulletSpan(20, ContextCompat.getColor(requireContext(),R.color.red_700),10),
                        9,18, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
                    spannableString.setSpan(
                        BulletSpan(20, ContextCompat.getColor(requireContext(),R.color.red_700),10),
                        21,31, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
                }else{
                    spannableString.setSpan(
                        BulletSpan(20, ContextCompat.getColor(requireContext(),R.color.red_700)),
                        9,19, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                spannableString.setSpan( ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red_700)),
                    8,19, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)

                spannableString.setSpan( ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.blue)),
                    21,textSpannable.length, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)



                val lineHeightInPx = 100
                spannableString.setSpan(LineHeightSpan.Standard(lineHeightInPx), 33, 45, SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(LineHeightSpan.Standard(lineHeightInPx), 47, 58, SPAN_EXCLUSIVE_EXCLUSIVE)


                val leadingMarginInPx = 200
                spannableString.setSpan(LeadingMarginSpan.Standard(leadingMarginInPx), 60, 71, SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(LeadingMarginSpan.Standard(leadingMarginInPx), 73, textSpannable.length, SPAN_EXCLUSIVE_EXCLUSIVE)

                val color = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_light)
                val color2 = ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
                spannableString.setSpan(BackgroundColorSpan(color), 47, 58, SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(BackgroundColorSpan(color2), 33, 45, SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(BackgroundColorSpan(color), 60, 71, SPAN_EXCLUSIVE_EXCLUSIVE)

                binding.lifeHack.explanation.text=spannableString
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

