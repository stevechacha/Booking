package com.chacha.booking.feature_bookings.presentation.fragment.one_way.presentation.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.core.utils.observeEvent
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.OneWayFragmentBinding
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.departure.DepartureBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.destinations.DestinationBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.passengers.PassengersBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.vehecle_type.VehicleTypeFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class OneWayFragment : Fragment(R.layout.one_way_fragment) {
    private val binding by viewBinding(OneWayFragmentBinding::bind)
    private val viewModel: OneWayViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setUpObservers()

        setDepatureDate()

        passengerBottomSheetDialog()

        showVehicle()
        departureStation()
        destinationStation()

//        oneWayDateBottomSheetDialog()


    }

    private fun departureStation() {
        binding.fromDestination.setOnClickListener {
            val dapartureDialog = DepartureBottomSheet()
            dapartureDialog.show(parentFragmentManager, dapartureDialog.tag)
        }

    }

    private fun destinationStation() {
        binding.toDestination.setOnClickListener {
            val destinationDialog = DestinationBottomSheet()
            destinationDialog.show(parentFragmentManager, destinationDialog.tag)

        }
    }

    private fun showVehicle() {

        binding.textVehicleType.setOnClickListener {
            val vehicleBottomSheetFragment = VehicleTypeFragment()
            vehicleBottomSheetFragment.show(parentFragmentManager, vehicleBottomSheetFragment.tag)
        }


    }

    /* @SuppressLint("SimpleDateFormat")
     private fun oneWayDateBottomSheetDialog() {
         binding.apply {
             textViewDepartureDate.setOnClickListener {
                 val datePicker =
                     MaterialDatePicker.Builder.dateRangePicker()
                         .setTitleText("Select date")
                         .build()

                val bottomDialog= datePicker.show(parentFragmentManager, datePicker.toString())
                 // Get the selected date


                 textViewDepartureDate.text = bottomDialog.toString()

             }
         }
     }*/


    private fun passengerBottomSheetDialog() {
        binding.passenger.setOnClickListener {
            val passengerBottomSheetFragment = PassengersBottomSheet()
            passengerBottomSheetFragment.show(
                parentFragmentManager,
                passengerBottomSheetFragment.tag
            )
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun setDepatureDate() {
        val datePicker = binding.textViewDepartureDate
        val currentDate = getCurrentDateInEnglish()
        datePicker.text = currentDate
        binding.textViewDepartureDate.setOnClickListener {
            showDatePickerDialog()
        }


    }

    @SuppressLint("SimpleDateFormat")
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                // Convert the selected date to a Date object
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
                    constraintsBuilder.build()
                }.time

                // Format the selected date as a string
                val dateFormat = SimpleDateFormat("dd MMM yyyy")
                val formattedSelectedDate = dateFormat.format(selectedDate)

                // Set the text of the TextView to the formatted selected date
                binding.textViewDepartureDate.text = formattedSelectedDate
            },
            year,
            month,
            day
        )
        calendar.timeInMillis = System.currentTimeMillis()
        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }


    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                OneWayUIState.Loading -> {
                    //renderLoading()
                }
                is OneWayUIState.Error -> {
                    //renderError()
//                    renderError(it.message)
                }
                is OneWayUIState.Message -> {
//                    renderSuccess(it.message)
                }
                is OneWayUIState.Destination -> {

                }

                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is OneWayActions.Navigate -> findNavController().navigate(it.destination)
//                is ReturnsActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

    private fun renderDestination(cityStation: String, cityName: String) {

    }

}

@SuppressLint("SimpleDateFormat")
fun getCurrentDateInEnglish(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM yyyy")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateThreeDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 3)
    val dateFormat = SimpleDateFormat("dd MMM yyyy")
    return dateFormat.format(calendar.time)
}