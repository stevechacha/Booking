package com.chacha.booking.search_results.date_five

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R

class FifthDateFragment : Fragment() {

    companion object {
        fun newInstance() = FifthDateFragment()
    }

    private lateinit var viewModel: FifthDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fifth_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FifthDateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}