package com.chacha.booking.feature_auth.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.booking.core.utils.Event
import com.chacha.booking.core.utils.asEvent
import com.chacha.booking.core.utils.handleThrowable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class WelcomeViewModel : ViewModel(){
    private val _uiState = MutableLiveData<WelcomeUIState>()
    val uiState: LiveData<WelcomeUIState> = _uiState

    private val _interactions = MutableLiveData<Event<WelcomeActions>>()
    val interactions: LiveData<Event<WelcomeActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(WelcomeUIState.Error(exception.handleThrowable()))

        val errorPair = exception.handleThrowable()
    }

    fun navigateToLogin() {
        val toLogin = WelcomeFragmentDirections.welcomeFragmentToLoginFragment()
        val action = WelcomeActions.Navigate(toLogin)
        _interactions.postValue(action.asEvent())

    }

    fun navigateToRegister() {
        val toRegister = WelcomeFragmentDirections.welcomeFragmentToSignUpFragment()
        val action = WelcomeActions.Navigate(toRegister)
        _interactions.postValue(action.asEvent())

    }

}

sealed class WelcomeActions {
    data class Navigate(val destination: NavDirections) : WelcomeActions()
}

sealed class WelcomeUIState {

    object Loading : WelcomeUIState()

    data class Error(val title: String = "Try again") : WelcomeUIState()

    data class LimitError(val message: String) : WelcomeUIState()
}