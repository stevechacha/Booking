package com.chacha.presentation.booking

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chacha.presentation.booking.bus_repository.BookingRepository
import com.chacha.presentation.booking.model.BusBooking
import com.chacha.presentation.booking.model.generateBusBookings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
class BookingUiViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    private val repository: BookingRepository = BookingRepository()


    private var searchJob: Job? = null

    private val flights = generateBusBookings()

    init {
        val availableDepartureList = flights
        val availableDestinationList = flights
        _uiState.value = _uiState.value.copy(
            availableDeparture = availableDepartureList,
            availableDestinations = availableDestinationList
        )
    }

    fun handleUiEvent(event: BookingUiEvent) {
        when (event) {
            is BookingUiEvent.BookBus -> {  //bookBus(event.bookingDetails)
            }
            is BookingUiEvent.DeparturePlaceSelected -> {
                _uiState.update { state ->
                    val updatedAvailableDestinations =
                        state.availableDestinations.filter { it.departureCity.city != event.place }
                    state.copy(
                        departurePlace = event.place,
                        availableDestinations = updatedAvailableDestinations
                    )
                }
            }

            is BookingUiEvent.DeparturePickupStationSelected -> {
                _uiState.update { it.copy(departurePickupStation = event.station) }
            }

            is BookingUiEvent.DestinationPickupStationSelected -> {
                _uiState.update { it.copy(destinationPickupStation = event.station) }
            }

            is BookingUiEvent.DepartureDropOffStationSelected -> {
                _uiState.update { it.copy(departureDropOffStation = event.station) }
            }

            is BookingUiEvent.DestinationDropOffStationSelected -> {
                _uiState.update { it.copy(destinationDropOffStation = event.station) }
            }

            is BookingUiEvent.DepartureDateSelected -> {
                _uiState.update { it.copy(departureDate = event.date) }
            }

            is BookingUiEvent.ReturnDateSelected -> {
                _uiState.update {
                    it.copy(
                        departureDate = event.departureDate,
                        returnDate = event.returnDate
                    )
                }

            }

            is BookingUiEvent.PassengerCountSelected -> {
                _uiState.update { it.copy(passengerCount = event.count) }
            }

            is BookingUiEvent.VehicleTypeSelected -> {
                _uiState.update { it.copy(vehicleType = event.type) }
            }

            is BookingUiEvent.DestinationPlaceSelected -> {
                _uiState.update { state ->
                    val updatedAvailableDepartures =
                        state.availableDeparture.filter { it.destinationCity.city != event.place }
                    state.copy(
                        destinationPlace = event.place,
                        availableDeparture = updatedAvailableDepartures
                    )
                }
            }


            is BookingUiEvent.SearchDepartureDestination -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val searchQuery = event.searchDepartureDestination.trim().lowercase()
                    val filteredDeparturePlaces = flights
                        .filter { it.departureCity.city.lowercase().contains(searchQuery) }
                    _uiState.update { state ->
                        state.copy(
                            availableDeparture = filteredDeparturePlaces,
                        )
                    }
                }

            }

            is BookingUiEvent.SearchDestination -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val searchQuery = event.searchDestination.trim().lowercase()
                    val filteredDestinationPlaces = flights
                        .filter { it.destinationCity.city.lowercase().contains(searchQuery) }

                    _uiState.update { state ->
                        state.copy(
                            availableDestinations = filteredDestinationPlaces
                        )
                    }
                }

            }

            BookingUiEvent.GetDepartureDestinationList -> {
                viewModelScope.launch {
                    val updatedFlights = fetchUpdatedFlights() // Fetch updated flight data
                    _uiState.update {
                        it.copy(
                            availableDeparture = updatedFlights,
                            availableDestinations = updatedFlights
                        )
                    }
                }
            }

            is BookingUiEvent.OnClick -> {
            }

            is BookingUiEvent.Save -> {
                _uiState.update { it.copy( bookingDetails = event.bookingDetails) }
            }

            is BookingUiEvent.OnClickWithParam -> {

            }
            is BookingUiEvent.OnEventPageChange -> {
                _uiState.update { it.copy(settledPage = event.settledPage) }
            }
            is BookingUiEvent.GenderType -> {
                _uiState.update { it.copy(gender = event.gender) }
            }
            is BookingUiEvent.Passenger -> {
                _uiState.update { it.copy(seatDetailList = event.seatDetail )}
            }
            is BookingUiEvent.OnPassengerChanged -> {}
            is BookingUiEvent.OnPassengerIdNumberChanged -> {
                _uiState.update { it.copy(idNumber = event.idNumber) }
            }
            is BookingUiEvent.OnPassengerMobilePhoneChanged -> {
                _uiState.update { it.copy(mobileNumber = event.mobileNumber) }
            }
            is BookingUiEvent.OnPassengerNameChanged -> {
                _uiState.update { it.copy(name = event.name) }
            }
            is BookingUiEvent.OnPassengerNationalityChanged -> {
                _uiState.update { it.copy(nationality = event.nationality) }
            }
            is BookingUiEvent.OnPassengerSeatLabelChanged -> {
                _uiState.update { it.copy(seatNumber = event.seatLabel) }
            }

            is BookingUiEvent.PassengerCustomer -> {

            }
        }
    }


        private suspend fun bookBus(bookingDetails: BusBooking) {
            _uiState.update { it.copy(isLoading = false) }
          /*  try {
                // Call the repository or use a use case to perform the booking logic
                val bookedBus = bookingRepository.bookBus(bookingDetails)

                // Update the UI state with the successful result
                _uiState.postValue(BookingUiState.Success(bookedBus))
            } catch (e: Exception) {
                // Handle any errors and update the UI state accordingly
                _uiState.postValue(BookingUiState.Error(e.message ?: "An error occurred"))
            }*/

    }

    fun searchFlights(departure: String, destination: String, departureDate: String): List<BusBooking> {
        val results = flights.filter { flight ->
            flight.departureCity.city == departure &&
                    flight.destinationCity.city == destination &&
                    flight.time.toLocalDate().toString() == departureDate
        }
        updateSearchResults(results) // Update the searchResults property
        return results // Return the search results
    }

    private fun updateSearchResults(results: List<BusBooking>) {
        _uiState.update { currentState ->
            currentState.copy(searchResults = results)
        }
    }

    private suspend fun fetchUpdatedFlights(): List<BusBooking> {
        // Replace with your data retrieval logic
        return generateBusBookings() // For example, fetch the flights again
    }


}