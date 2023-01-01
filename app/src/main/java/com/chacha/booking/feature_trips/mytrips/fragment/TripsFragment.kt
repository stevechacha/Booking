package com.chacha.booking.feature_trips.mytrips.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.chacha.booking.R
import com.chacha.booking.core.utils.observeEvent
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.TripsFragmentBinding
import com.chacha.booking.feature_trips.mytrips.adapters.MyTripsAdapterPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripsFragment : Fragment(R.layout.trips_fragment) {
    private val binding by viewBinding(TripsFragmentBinding::bind)
    private val viewModel: TripsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = MyTripsAdapterPagerAdapter(requireContext(), childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        setUpObservers()

    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                MyTripsUIState.Loading -> {
                    //renderLoading()
                }
                is MyTripsUIState.Error -> {
                    //renderError()
//                    renderError(it.message)
                }
                is MyTripsUIState.Message ->{
//                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is MyTripsActions.Navigate -> findNavController().navigate(it.destination)
//                is ReturnActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

}

