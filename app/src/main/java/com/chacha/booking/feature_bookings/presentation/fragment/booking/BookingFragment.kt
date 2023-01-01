package com.chacha.booking.feature_bookings.presentation.fragment.booking

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.BookingFragmentBinding
import com.chacha.booking.feature_bookings.presentation.adapters.BookingSearchPagerAdapter
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.booking_menu.MenuBookingBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.passengers.PassengersBottomSheet
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment : Fragment(R.layout.booking_fragment) {
    private val binding by viewBinding(BookingFragmentBinding::bind)
    private val viewModel: BookingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            val menuBooking = MenuBookingBottomSheet()
            menuBooking.show(parentFragmentManager, menuBooking.tag)

        }


        val sectionsPagerAdapter = BookingSearchPagerAdapter(requireContext(), childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        setUpObservers()
    }

    private fun setUpObservers() {
    }


}