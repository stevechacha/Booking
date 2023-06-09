package com.chacha.presentation.booking.departure_destination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DepartureDestinationViewModel : ViewModel() {

    val state = MutableStateFlow(DepartureDestinationState())

    private var searchJob: Job? = null

    fun onEvent(event: DepartureDestinationEvent) {
        when (event) {
            DepartureDestinationEvent.GetDepartureDestinationList -> {
                state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    state.update { it.copy(isLoading = false) }


                }
            }
            is DepartureDestinationEvent.OnEventDepartureDestinationChange -> {
                state.update { it.copy(departureDestination =  event.departureDestination) }
            }
            is DepartureDestinationEvent.SearchDepartureDestination -> {
                if (event.searchDepartureDestination.isEmpty()) {
                    state.update { it.copy(departureDestinationList = listOf()) }
                    return
                } else {
                    searchJob?.cancel()
                    searchJob = null
                    searchJob = search(event.searchDepartureDestination)
                }
                state.update { it.copy(searchDepartureDestination =  event.searchDepartureDestination) }

            }
        }
    }

    private fun search(searchDepartureDestination: String): Job? {
        return null
    }
}
