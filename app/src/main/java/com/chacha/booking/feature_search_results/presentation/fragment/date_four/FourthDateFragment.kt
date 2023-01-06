package com.chacha.booking.feature_search_results.presentation.fragment.date_four

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R

class FourthDateFragment : Fragment() {

    companion object {
        fun newInstance() = FourthDateFragment()
    }

    private lateinit var viewModel: FourthDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fourth_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FourthDateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}