package com.chacha.presentation.booking

import androidx.annotation.StringRes
import com.chacha.presentation.R
import java.time.LocalDate


sealed class BookingUiEvent {
     object GetDepartureDestinationList: BookingUiEvent()
    object OnClick: BookingUiEvent()
    data class OnClickWithParam(val param: String): BookingUiEvent()
    data class OnEventPageChange(val settledPage: Int): BookingUiEvent()
    data class DeparturePlaceSelected(val place: String) : BookingUiEvent()
    data class DestinationPlaceSelected(val place: String) : BookingUiEvent()
    data class DeparturePickupStationSelected(val station: String) : BookingUiEvent()
    data class DepartureDropOffStationSelected(val station: String) : BookingUiEvent()
    data class DestinationPickupStationSelected(val station: String) : BookingUiEvent()
    data class DestinationDropOffStationSelected(val station: String) : BookingUiEvent()
    data class DepartureDateSelected(val date: LocalDate) : BookingUiEvent()
    data class ReturnDateSelected(val departureDate: LocalDate,val returnDate: LocalDate) : BookingUiEvent()
    data class PassengerCountSelected(val count: Int) : BookingUiEvent()
    data class VehicleTypeSelected(val type: VehicleType) : BookingUiEvent()
    data class SearchDepartureDestination(val searchDepartureDestination: String): BookingUiEvent()
    data class SearchDestination(val searchDestination: String): BookingUiEvent()


}

enum class BookingPages(@StringRes val title: Int) {
    RETURN(title = R.string.returns),
    ONEWAY(title = R.string.one_way),
    MULTI_CITY(title = R.string.multi_city)
}
