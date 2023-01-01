package com.chacha.booking.feature_bookings.presentation.bottom_sheet.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chacha.booking.R
import com.chacha.booking.core.utils.Resource
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.DestinationBottomSheetFragmentBinding
import com.chacha.booking.feature_bookings.presentation.adapters.DestinationStationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DestinationBottomSheet : BottomSheetDialogFragment(R.layout.destination_bottom_sheet_fragment) {

    private val viewModel: DestinationBottomSheetViewModel by viewModels()
    private val binding by viewBinding ( DestinationBottomSheetFragmentBinding::bind )
    private val adapter by lazy { DestinationStationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.destination.observe(viewLifecycleOwner, Observer {  result ->

            when(result){
                is Resource.Success ->{
                    adapter.submitList(result.data)
                    binding.destinationRecyclerView.adapter = adapter
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    binding.destinationProgressBar.isVisible = false

                }
                is Resource.Loading ->{
                    binding.destinationProgressBar.isVisible = true
                }
            }

        })
    }


}