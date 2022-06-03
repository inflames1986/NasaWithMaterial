package com.inflames1986.nasawithmaterial.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.FragmentSystemBinding

class SystemFragment : Fragment() {


    private var _binding: FragmentSystemBinding? = null
    private val binding: FragmentSystemBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}