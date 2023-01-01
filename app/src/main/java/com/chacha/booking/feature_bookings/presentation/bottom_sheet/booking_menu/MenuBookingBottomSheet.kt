package com.chacha.booking.feature_bookings.presentation.bottom_sheet.booking_menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBookingBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = MenuBookingBottomSheet()
    }

    private lateinit var viewModel: MenuBookingBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_booking_bottom_sheet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuBookingBottomSheetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}