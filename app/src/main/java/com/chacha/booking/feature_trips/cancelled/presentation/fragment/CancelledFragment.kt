package com.chacha.booking.feature_trips.cancelled.presentation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelledFragment : Fragment(R.layout.cancelled_fragment) {

    companion object {
        fun newInstance() = CancelledFragment()
    }

    private lateinit var viewModel: CancelledViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cancelled_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CancelledViewModel::class.java)
        // TODO: Use the ViewModel
    }

}