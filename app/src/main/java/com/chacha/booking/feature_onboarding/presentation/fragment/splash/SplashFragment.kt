package com.chacha.booking.feature_onboarding.presentation.fragment.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.SplashFragmentBinding


class SplashFragment : Fragment(R.layout.splash_fragment) {
    private val binding by viewBinding(SplashFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(R.id.splashFragmentToBookingFragment)
            }else{
                findNavController().navigate(R.id.splashFragmentToViewPagerFragment)
            }
        }, 3000)

    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }


}