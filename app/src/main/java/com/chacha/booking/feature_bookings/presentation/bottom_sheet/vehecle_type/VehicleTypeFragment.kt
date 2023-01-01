package com.chacha.booking.feature_bookings.presentation.bottom_sheet.vehecle_type

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
import com.chacha.booking.databinding.VehicleTypeFragmentBinding
import com.chacha.booking.feature_bookings.presentation.adapters.DestinationStationAdapter
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.destinations.DestinationBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleTypeFragment : BottomSheetDialogFragment(R.layout.vehicle_type_fragment) {
    private val viewModel: VehicleTypeViewModel by viewModels()
    private val binding by viewBinding ( VehicleTypeFragmentBinding::bind )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
    }

}