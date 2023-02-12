package com.chacha.booking.welcome

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.WindowCompat
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.utils.observeEvent
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.WelcomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.welcome_fragment) {
    private val binding by viewBinding(WelcomeFragmentBinding::bind)
    private val viewModel: WelcomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

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