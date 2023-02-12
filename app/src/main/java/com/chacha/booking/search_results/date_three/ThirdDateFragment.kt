package com.chacha.booking.search_results.date_three

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R

class ThirdDateFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdDateFragment()
    }

    private lateinit var viewModel: ThirdDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThirdDateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}