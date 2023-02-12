package com.chacha.booking.bookings.presentation.fragment.one_way.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.booking.utils.Event
import com.chacha.booking.utils.handleThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class OneWayViewModel : ViewModel() {

    private val _uiState = MutableLiveData<OneWayUIState>()
    val uiState: LiveData<OneWayUIState> = _uiState

    private val _interactions = MutableLiveData<Event<OneWayActions>>()
    val interactions: LiveData<Event<OneWayActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(OneWayUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }




}

sealed class OneWayActions {
    data class Navigate(val destination: NavDirections) : OneWayActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): ReturnsActions()
}

sealed class OneWayUIState {

    object Loading : OneWayUIState()

    data class Error(val message: String = "Try again") : OneWayUIState()

    data class LimitError(val message: String) : OneWayUIState()

    data class Message(val message: String): OneWayUIState()

    data class Destination(val fromCity: String, val fromStation: String): OneWayUIState()
}