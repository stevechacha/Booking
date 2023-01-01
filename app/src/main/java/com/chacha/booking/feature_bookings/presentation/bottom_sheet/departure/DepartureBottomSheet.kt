package com.chacha.booking.feature_bookings.presentation.bottom_sheet.departure

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chacha.booking.R
import com.chacha.booking.core.utils.Resource
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.DepartureBottomSheetFragmentBinding
import com.chacha.booking.feature_bookings.presentation.adapters.DepartureStationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartureBottomSheet : BottomSheetDialogFragment(R.layout.departure_bottom_sheet_fragment) {
    private val viewModel: DepartureBottomSheetViewModel by viewModels()
    private val binding by viewBinding ( DepartureBottomSheetFragmentBinding::bind )
    private val adapter by lazy { DepartureStationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.departure.observe(viewLifecycleOwner, Observer {  result ->
            when(result){
                is Resource.Success ->{
                    adapter.submitList(result.data)
                    binding.departureRecyclerView.adapter = adapter
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    binding.departureProgressBar.isVisible = false

                }
                is Resource.Loading ->{
                    binding.departureProgressBar.isVisible = true
                }
            }

        })
    }


}