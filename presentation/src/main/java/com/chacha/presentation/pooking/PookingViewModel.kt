package com.chacha.presentation.pooking

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PookingViewModel : ViewModel() {

    private val _pookingState = MutableStateFlow(PookingState() )
    val pookingState: MutableStateFlow<PookingState> = _pookingState


    fun onEvent(event: PookingEvent){
        when(event){
            is PookingEvent.From -> {

            }
            is PookingEvent.To -> {
               _pookingState.value = pookingState.value.copy(
                   to = event.to
               )
            }
            is PookingEvent.DepartureDate -> {
                _pookingState.value = pookingState.value.copy(
                    departureDate = event.departureDate
                )
            }
            is PookingEvent.Passenger -> {
                _pookingState.value = pookingState.value.copy(
                    passenger = event.passenger
                )
            }
            is PookingEvent.ReturnDate -> {
                _pookingState.value = pookingState.value.copy(
                    returnDate = event.returnDate
                )
            }
            is PookingEvent.VehicleType -> {
                _pookingState.value = pookingState.value.copy(
                    vehicleTypes = event.vehicleType
                )

            }
            is PookingEvent.OnSearchQuery -> {
                _pookingState.value = pookingState.value.copy(
                    from = event.query
                )


            }

            else -> {}
        }
    }


}

