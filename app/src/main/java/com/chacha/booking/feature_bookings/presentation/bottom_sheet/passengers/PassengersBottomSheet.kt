package com.chacha.booking.feature_bookings.presentation.bottom_sheet.passengers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.DestinationBottomSheetFragmentBinding
import com.chacha.booking.databinding.PassengersBottomSheetFragmentBinding
import com.chacha.booking.feature_bookings.presentation.adapters.DestinationStationAdapter
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.destinations.DestinationBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengersBottomSheet : BottomSheetDialogFragment(R.layout.passengers_bottom_sheet_fragment) {
    private val viewModel: PassengersBottomSheetViewModel by viewModels()
    private val binding by viewBinding ( PassengersBottomSheetFragmentBinding::bind )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {

    }


}