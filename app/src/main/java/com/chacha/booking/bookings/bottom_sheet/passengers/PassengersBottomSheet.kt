package com.chacha.booking.bookings.bottom_sheet.passengers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.PassengersBottomSheetFragmentBinding
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