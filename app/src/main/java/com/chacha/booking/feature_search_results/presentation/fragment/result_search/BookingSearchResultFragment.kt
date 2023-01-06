package com.chacha.booking.feature_search_results.presentation.fragment.result_search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.BookingSearchResultFragmentBinding
import com.chacha.booking.feature_search_results.presentation.adapters.BookingSearchResultPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

class BookingSearchResultFragment :
    BottomSheetDialogFragment(R.layout.booking_search_result_fragment) {
    private val binding by viewBinding(BookingSearchResultFragmentBinding::bind)
    private val viewModel: BookingSearchResultViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bookingSearchResultPagerAdapter = BookingSearchResultPagerAdapter(
            requireContext(), childFragmentManager
        )
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = bookingSearchResultPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)


    }


}


@SuppressLint("SimpleDateFormat")
fun getCurrentDateInEnglish(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM yyyy")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateThreeDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 3)
    val dateFormat = SimpleDateFormat("dd MMM yyyy")
    return dateFormat.format(calendar.time)
}