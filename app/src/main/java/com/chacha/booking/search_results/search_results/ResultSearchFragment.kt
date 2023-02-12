package com.chacha.booking.search_results.search_results

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.ResultSearchFragmentBinding
import com.chacha.booking.search_results.adapters.BookSearchViewPagerAdapter
import com.chacha.booking.search_results.adapters.getDateFifthDaysAhead
import com.chacha.booking.search_results.adapters.getDateFourthDaysAhead
import com.chacha.booking.search_results.date_five.FifthDateFragment
import com.chacha.booking.search_results.date_four.FourthDateFragment
import com.chacha.booking.search_results.date_three.ThirdDateFragment
import com.chacha.booking.search_results.date_two.SecondDateFragment
import com.chacha.booking.search_results.first_date.FirstDateFragment
import com.chacha.booking.search_results.result_search.getCurrentDateInEnglish
import com.chacha.booking.search_results.result_search.getDateThreeDaysAhead
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*

class ResultSearchFragment : Fragment(R.layout.result_search_fragment) {
    private val binding by  viewBinding(ResultSearchFragmentBinding::bind)
    private val viewModel: ResultSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpTabLayout()


    }

    private fun setUpTabLayout() {
        binding.apply {
            val fragmentList = arrayListOf<Fragment>(
                FirstDateFragment(),
                SecondDateFragment(),
                ThirdDateFragment(),
                FourthDateFragment(),
                FifthDateFragment()
            )

            val adapter = BookSearchViewPagerAdapter(fragmentList,
                requireActivity().supportFragmentManager, lifecycle
            )
            binding.viewPager2.adapter = adapter

            TabLayoutMediator(binding.tabs, viewPager2) { tab, position ->
                when(position){
                    0 ->{
                        tab.text = "${getCurrentDateInEnglish()}\nEnaCoach"
                    }
                    1 ->{
                        tab.text = getDateTwoDaysAhead()
                    }
                    2 ->{
                        tab.text = getDateThreeDaysAhead()
                    }
                    3 ->{
                        tab.text = getDateFourthDaysAhead()
                    }
                    4->{
                        tab.text = getDateFifthDaysAhead()
                    }
                }

            }.attach()

        }
    }
}

@SuppressLint("SimpleDateFormat")
fun getDateTwoDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 1)
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

