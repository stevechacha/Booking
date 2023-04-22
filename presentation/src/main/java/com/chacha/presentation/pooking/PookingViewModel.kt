package com.chacha.presentation.pooking

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.nio.channels.Channel

class PookingViewModel : ViewModel() {

    private val _pookingState = mutableStateOf(PookingState.Loading)
    val pookingState: State<PookingState> = _pookingState

    private val _interactions = MutableLiveData<BottomSheetAction>()
    val interactions: LiveData<BottomSheetAction> = _interactions


    fun onEvent(event: BottomSheetAction){
        when(event){
            is BottomSheetAction.From -> {

            }
            is BottomSheetAction.To -> {
            }
            is BottomSheetAction.DepartureDate -> {
            }
            is BottomSheetAction.Passenger -> {
            }
            is BottomSheetAction.ReturnDate -> {
            }
            is BottomSheetAction.VehicleType -> {

            }

            else -> {}
        }
    }





}

sealed interface PookingEvent {
    object GetPooking : PookingEvent
}

sealed class PookingState {
    object Loading : PookingState()
    data class Error(val error: String) : PookingState()
}