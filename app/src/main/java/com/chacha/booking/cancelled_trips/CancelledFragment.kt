package com.chacha.booking.cancelled_trips

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.databinding.CancelledFragmentBinding
import com.chacha.booking.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelledFragment : Fragment(R.layout.cancelled_fragment) {

    private val viewModel: CancelledViewModel by viewModels()
    private val binding by viewBinding(CancelledFragmentBinding::bind)
    private val adapter by lazy { CancelledTripsAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }

    private fun setUpObservers() {

    }

}