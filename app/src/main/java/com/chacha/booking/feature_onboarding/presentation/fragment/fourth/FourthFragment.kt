package com.chacha.booking.feature_onboarding.presentation.fragment.fourth

import android.content.Context
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
import com.chacha.booking.databinding.FourthFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthFragment : Fragment(R.layout.fourth_fragment) {
    private val binding by viewBinding (FourthFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager2)
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.viewPagerFragmentToWelcomeFragment)
            onBoardingFinished()
        }

    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", false)
        editor.apply()
    }
}