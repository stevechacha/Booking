package com.chacha.booking.feature_auth.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.core.utils.observeEvent
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.WelcomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.welcome_fragment) {
    private val binding by viewBinding(WelcomeFragmentBinding::bind)
    private val viewModel: WelcomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        onClick()



    }

    private fun onClick(){
        binding.apply {
            signInBtn.setOnClickListener {
                viewModel.navigateToLogin()
            }


            signUpBtn.setOnClickListener {
                viewModel.navigateToRegister()
            }
        }


    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                WelcomeUIState.Loading ->{}  //renderLoading()
                is WelcomeUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is WelcomeActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}