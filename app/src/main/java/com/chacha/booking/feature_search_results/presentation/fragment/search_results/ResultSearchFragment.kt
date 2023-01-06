package com.chacha.booking.feature_search_results.presentation.fragment.search_results

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.ResultSearchFragmentBinding
import com.chacha.booking.feature_onboarding.presentation.adapters.ViewPagerAdapter
import com.chacha.booking.feature_onboarding.presentation.fragment.first.FirstFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.fourth.FourthFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.second.SecondFragment
import com.chacha.booking.feature_onboarding.presentation.fragment.third.ThirdFragment
import com.chacha.booking.feature_search_results.presentation.adapters.BookSearchViewPagerAdapter
import com.chacha.booking.feature_search_results.presentation.adapters.getDateFifthDaysAhead
import com.chacha.booking.feature_search_results.presentation.adapters.getDateFourthDaysAhead
import com.chacha.booking.feature_search_results.presentation.fragment.date_five.FifthDateFragment
import com.chacha.booking.feature_search_results.presentation.fragment.date_four.FourthDateFragment
import com.chacha.booking.feature_search_results.presentation.fragment.date_three.ThirdDateFragment
import com.chacha.booking.feature_search_results.presentation.fragment.date_two.SecondDateFragment
import com.chacha.booking.feature_search_results.presentation.fragment.first_date.FirstDateFragment
import com.chacha.booking.feature_search_results.presentation.fragment.result_search.getCurrentDateInEnglish
import com.chacha.booking.feature_search_results.presentation.fragment.result_search.getDateThreeDaysAhead
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber
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

