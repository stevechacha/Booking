package com.chacha.booking.forgot_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.utils.observeEvent
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.ForgotPasswordFragmentBinding
import com.chacha.booking.forgot_password.presentation.fragment.ForgetPasswordActions
import com.chacha.booking.forgot_password.presentation.fragment.ForgetPasswordUIState
import com.chacha.booking.forgot_password.presentation.fragment.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private val binding by viewBinding (ForgotPasswordFragmentBinding::bind)
    private val viewModel : ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                ForgetPasswordUIState.Loading ->{}  //renderLoading()
                is ForgetPasswordUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ForgetPasswordActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }


}