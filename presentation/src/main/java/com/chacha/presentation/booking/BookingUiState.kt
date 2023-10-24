package com.chacha.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import com.chacha.presentation.booking.booking_destination_list.Flight
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
val dateTime = LocalDateTime.now().toLocalDate()


@RequiresApi(Build.VERSION_CODES.O)
data class BookingUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val settledPage: Int = 0,
    val departurePlace: String = "",
    val destinationPlace: String = "",
    val departurePickupStation: String = "",
    val departureDropOffStation: String = "",
    val destinationPickupStation: String = "",
    val destinationDropOffStation: String = "",
    val departureDate: String = dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), // Format the date here
    val returnDate: String = "",
    val passengerCount: Int = 1,
    val vehicleType: VehicleType = VehicleType.BUS,
    val place: List<Flight> = emptyList(),
    val availableDeparture: List<Flight> = emptyList(),
    val availablePlaces: List<Flight> = emptyList(),
    val availableDestinations: List<Flight> = emptyList(),
    val availableDepartures: List<Flight> = emptyList(),
    val searchDepartureDestination : String = "",
    val searchDestination : String = "",
    val searchResults:List<Flight> = emptyList()


){
    val isFormValid: Boolean
        get() = departurePlace.isNotEmpty() &&
                destinationPlace.isNotEmpty() &&
                departureDate.isNotEmpty() &&
                passengerCount > 0


}

fun Long.parsedDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return format.format(date)
}


enum class VehicleType(val type: String) {
    BUS(type = "Bus"),
    TRAIN(type = "Train"),
    FLIGHT(type = "Flight")
}







