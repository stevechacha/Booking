package com.chacha.booking.mytrips.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chacha.booking.utils.Event
import com.chacha.booking.utils.handleThrowable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class TripsViewModel : ViewModel() {
    private val _uiState = MutableLiveData<MyTripsUIState>()
    val uiState: LiveData<MyTripsUIState> = _uiState

    private val _interactions = MutableLiveData<Event<MyTripsActions>>()
    val interactions: LiveData<Event<MyTripsActions>> = _interactions

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.postValue(MyTripsUIState.Error(exception.handleThrowable()))
        // val errorPair = exception.handleThrowable()
    }

}

sealed class MyTripsActions {
    data class Navigate(val destination: NavDirections) : MyTripsActions()
//    data class BottomNavigate(val bottomSheetDialogFragment: BottomSheetDialogFragment): ReturnActions()
}

sealed class MyTripsUIState {

    object Loading : MyTripsUIState()

    data class Error(val message: String = "Try again") : MyTripsUIState()

    data class LimitError(val message: String) : MyTripsUIState()

    data class Message(val message: String) : MyTripsUIState()
}
