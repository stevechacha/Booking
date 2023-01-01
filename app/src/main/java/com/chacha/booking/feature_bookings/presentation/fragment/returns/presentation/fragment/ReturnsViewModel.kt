package com.chacha.booking.feature_bookings.presentation.fragment.returns.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.booking.core.utils.Event
import com.chacha.booking.core.utils.handleThrowable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class ReturnsViewModel : ViewModel() {


    private val _uiState = MutableLiveData<ReturnsUIState>()
    val uiState: LiveData<ReturnsUIState> = _uiState

    private val _interactions = MutableLiveData<Event<ReturnsActions>>()
    val interactions: LiveData<Event<ReturnsActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(ReturnsUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }



}

sealed class ReturnsActions {
    data class Navigate(val destination: NavDirections) : ReturnsActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: PassengerBottomSheetFragment): ReturnsActions()
}

sealed class ReturnsUIState {

    object Loading : ReturnsUIState()

    data class Error(val message: String = "Try again") : ReturnsUIState()

    data class LimitError(val message: String) : ReturnsUIState()

    data class Message(val message: String): ReturnsUIState()
}