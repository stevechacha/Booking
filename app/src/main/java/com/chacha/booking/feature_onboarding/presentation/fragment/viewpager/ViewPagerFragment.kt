package com.chacha.booking.feature_onboarding.presentation.fragment.viewpager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.chacha.booking.R
import com.chacha.booking.databinding.ViewPagerFragmentBinding
import com.chacha.booking.feature_onboarding.presentation.adapters.ViewPagerAdapter
import com.chacha.booking.feature_onboarding.presentation.fragment.first.FirstFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.fourth.FourthFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.second.SecondFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.third.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ViewPagerFragment : Fragment(R.layout.view_pager_fragment) {

    lateinit var binding: ViewPagerFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ViewPagerFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment(),
            FourthFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position -> }.attach()
    }
}
