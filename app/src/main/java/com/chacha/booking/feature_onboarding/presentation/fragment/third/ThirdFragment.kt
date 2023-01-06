package com.chacha.booking.feature_onboarding.presentation.fragment.third

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.FirstFragmentBinding
import com.chacha.booking.databinding.ThirdFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment(R.layout.third_fragment) {

    private val binding by viewBinding(ThirdFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager2)
        binding.nextBtn.setOnClickListener {
            viewPager?.currentItem = 3
        }

        binding.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.viewPagerFragmentToWelcomeFragment)
        }
    }

}