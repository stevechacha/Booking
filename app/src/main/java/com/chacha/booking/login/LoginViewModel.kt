package com.chacha.booking.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.chacha.booking.data.remote.api.BookingApi
import com.chacha.booking.utils.Event
import com.chacha.booking.utils.asEvent
import com.chacha.booking.utils.handleThrowable
import com.chacha.booking.domain.repository.LoginRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val api: BookingApi,
    private val repository: LoginRepository,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _uiState: MutableLiveData<LoginUIState> = MutableLiveData(LoginUIState.Loading)
    val uiState: LiveData<LoginUIState> = _uiState

    private val _interactions = MutableLiveData<Event<LoginActions>>()
    val interactions: LiveData<Event<LoginActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(LoginUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }


    fun signIn(email: String, password: String) {
        _uiState.postValue(LoginUIState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val response = withContext(Dispatchers.IO) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        _interactions.postValue(
                            LoginActions.Navigate(
                                LoginFragmentDirections.loginFragmentToBookingFragment()
                            ).asEvent()
                        )
                    }.addOnCanceledListener {
                        _uiState.postValue(LoginUIState.Error(message = "message"))
                    }
            }
            if (response.isSuccessful) {
                _interactions.postValue(
                    LoginActions.Navigate(
                        LoginFragmentDirections.loginFragmentToBookingFragment()
                    ).asEvent()
                )
            } else {
                _uiState.postValue(LoginUIState.Error(message = "Error Message"))
            }

        }

    }


    fun loginUser(
        email: String? = "",
        phoneNumber: String? = "",
        password: String
    ) {
        _uiState.postValue(LoginUIState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val response = withContext(Dispatchers.IO) {
                api.login(
                    BookingApi.Login(
                        Email = email,
                        Password = password,
                        PhoneNumber = phoneNumber
                    )
                )
            }
            if (response.code() == 200) {
                _interactions.postValue(
                    LoginActions.Navigate(
                        LoginFragmentDirections.loginFragmentToBookingFragment()
                    ).asEvent()
                )

            } else if (response.code() == 403) {
                _uiState.postValue(LoginUIState.Error(response.message()))
            }
        }

    }

    fun login(email: String, password: String) {
        _uiState.postValue(LoginUIState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                repository.login(email, password)


            }
        }
    }

    fun navigateToRegister() {
        _interactions.postValue(
            LoginActions.Navigate(
                LoginFragmentDirections.loginFragmentToSignUpFragment()
            ).asEvent()
        )
    }


    fun navigateToBook() {
       _interactions.postValue(
           LoginActions.Navigate(
               LoginFragmentDirections.loginFragmentToBookingFragment()
           ).asEvent()
       )

    }

    fun navigateToForgotPassword() {
        _interactions.postValue(
            LoginActions.Navigate(
                LoginFragmentDirections.loginFragmentToForgotPasswordFragment()
            ).asEvent()
        )

    }
}

sealed class LoginActions {
    data class Navigate(val destination: NavDirections) : LoginActions()
    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment) :
        LoginActions()
}

sealed class LoginUIState {

    object Loading : LoginUIState()

    data class Error(val message: String = "Try again") : LoginUIState()

    data class LimitError(val message: String) : LoginUIState()

    data class Message(val message: String) : LoginUIState()
}