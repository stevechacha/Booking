package com.chacha.booking.feature_auth.signup.presentation.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.core.utils.*
import com.chacha.booking.databinding.SignUpFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.sign_up_fragment) {
    private val binding by viewBinding(SignUpFragmentBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        validateUser()

    }

    private fun validateUser() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val name = binding.nameTextFieldEditText.text.toString().trim()
        binding.confirmBtm.setOnClickListener {
            viewModel.signUp(
                email,password, name
            )
        }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
//            reload();
        }
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                SignUpUIState.Loading -> {}  //renderLoading()
                is SignUpUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is SignUpActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }

    private fun onRegisterFinished() {
        val sharedPref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()

    }
}

