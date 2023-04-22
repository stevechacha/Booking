package com.chacha.presentation.pooking

sealed class BottomSheetAction {
    object From : BottomSheetAction()
    object To : BottomSheetAction()
    object DepartureDate : BottomSheetAction()
    object ReturnDate : BottomSheetAction()
    object Passenger : BottomSheetAction()
    object VehicleType : BottomSheetAction()

}