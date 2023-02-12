package com.chacha.booking.login

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.utils.observeEvent
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.LoginFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(LoginFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)



        setUpObservers()
        /* navigateToRegister()
         loginUser()*/
//        validateUser()
        navigateToBook()
        navigateToSignUp()

//        validateUser()


    }

    private fun navigateToSignUp() {
        binding.apply {
            signUpTextView.setOnClickListener {
                viewModel.navigateToRegister()
            }
        }
    }

    private fun navigateToForgotPassword(){
        binding.apply {
            forgotPassword.setOnClickListener {
                viewModel.navigateToForgotPassword()
            }
        }
    }

    /*   private fun validateUser() {
           binding.confirmBtm.setOnClickListener {
               val email = binding.emailUser.text.toString().trim()
               val password = binding.passWordUser.text.toString().trim()
               viewModel.signIn(
                   email, password
               )
           }

       }*/


    private fun navigateToBook() {
        binding.confirmBtm.setOnClickListener {
            viewModel.navigateToBook()
        }

    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                LoginUIState.Loading -> {
                    //renderLoading()
                }
                is LoginUIState.Error -> {
                    //renderError()
                    renderError(it.message)
                }
                is LoginUIState.Message -> {
                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is LoginActions.Navigate -> findNavController().navigate(it.destination)
                is LoginActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

    private fun renderError(message: String) {
        binding.apply {
//            loading.isVisible = false
//            loginBtn.isEnable = true
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(parentFragmentManager, bottomSheetDialogFragment.tag)

    }

    private fun renderSuccess(message: String) {
        binding.apply {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

}