package com.chacha.booking.search_results.first_date

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chacha.booking.R

class FirstDateFragment : Fragment() {

    companion object {
        fun newInstance() = FirstDateFragment()
    }

    private lateinit var viewModel: FirstDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirstDateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}