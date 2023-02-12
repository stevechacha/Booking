package com.chacha.booking.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections

import com.chacha.booking.utils.Event
import com.chacha.booking.utils.asEvent
import com.chacha.booking.utils.handleThrowable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _uiState = MutableLiveData<SignUpUIState>()
    val uiState: LiveData<SignUpUIState> = _uiState

    private val _interactions = MutableLiveData<Event<SignUpActions>>()
    val interactions: LiveData<Event<SignUpActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(SignUpUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

    fun signUp(email: String, password: String,name: String){
        _uiState.postValue(SignUpUIState.Loading)
        viewModelScope.launch (exceptionHandler){
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
        }
    }

    fun navigateToHome() {
        val toHome = SignUpFragmentDirections.signUpFragmentToBookingFragment()
        val action = SignUpActions.Navigate(toHome)
        _interactions.postValue(action.asEvent())
    }

    fun navigateToLogin() {
        _interactions.postValue(
            SignUpActions.Navigate(
                SignUpFragmentDirections.signUpFragmentToLoginFragment()
            ).asEvent()
        )
    }


}

sealed class SignUpActions {
    data class Navigate(val destination: NavDirections) : SignUpActions()
}

sealed class SignUpUIState {

    object Loading : SignUpUIState()

    data class Error(val title: String = "Try again") : SignUpUIState()

    data class LimitError(val message: String) : SignUpUIState()
}