package com.chacha.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import com.chacha.presentation.booking.booking_destination_list.BusBooking
import com.chacha.presentation.util.toDate
import com.chacha.presentation.util.toLocalDate
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
val dateTime = LocalDateTime.now().toLocalDate()


@RequiresApi(Build.VERSION_CODES.O)
data class BookingUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val settledPage: Int = 0,
    val departurePlace: String = "",
    val destinationPlace: String = "",
    val departurePickupStation: String = "",
    val departureDropOffStation: String = "",
    val destinationPickupStation: String = "",
    val destinationDropOffStation: String = "",
    val departureDate: LocalDate = LocalDate.now(), // Format the date here
    val returnDate: LocalDate = LocalDate.now().plusDays(3),
    val passengerCount: Int = 1,
    val vehicleType: VehicleType = VehicleType.BUS,
    val place: List<BusBooking> = emptyList(),
    val availableDeparture: List<BusBooking> = emptyList(),
    val availablePlaces: List<BusBooking> = emptyList(),
    val availableDestinations: List<BusBooking> = emptyList(),
    val availableDepartures: List<BusBooking> = emptyList(),
    val searchDepartureDestination: String = "",
    val searchDestination: String = "",
    val searchResults:List<BusBooking> = emptyList()


){
    val isFormValid: Boolean
        get() = departurePlace.isNotEmpty() &&
                destinationPlace.isNotEmpty() &&
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






