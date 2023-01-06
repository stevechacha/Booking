package com.chacha.booking.feature_bookings.presentation.fragment.one_way.presentation.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
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
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class OneWayFragment : Fragment(R.layout.one_way_fragment) {
    private val binding by viewBinding(OneWayFragmentBinding::bind)
    private val viewModel: OneWayViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setUpObservers()

        selectNumberOfPassenger()

        selectVehicle()

        selectDepartureStation()

        selectDestinationStation()

        selectDepartureDate()

        searchDate()


    }

    private fun searchDate() {
        // when floationg acition button is clicked
        binding.textViewDepartureDate.setOnClickListener {
            // Initiation date picker with
            // MaterialDatePicker.Builder.datePicker()
            // and building it using build()
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(parentFragmentManager, "DatePicker")

            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                // formatting date in dd-mm-yyyy format.
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it)).toString()
                Toast.makeText(requireContext(), "$date is selected", Toast.LENGTH_LONG).show()

                binding.textViewDepartureDate.text = date

            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(
                    requireContext(),
                    "${datePicker.headerText} is cancelled",
                    Toast.LENGTH_LONG
                ).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(requireContext(), "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun selectDepartureDate() {
        // Returning The Current Date
        val datePicker = binding.textViewDepartureDate
        val currentDate = getCurrentDateInEnglish()
        datePicker.text = currentDate

        /*binding.textViewDepartureDate.setOnClickListener {
            showDatePickerDialog()
        }*/
    }

    private fun selectDepartureStation() {
        binding.fromDestination.setOnClickListener {
            val selectDepartureStation = DepartureBottomSheet()
            selectDepartureStation.show(parentFragmentManager, selectDepartureStation.tag)
        }

    }

    private fun selectDestinationStation() {
        binding.toDestination.setOnClickListener {
            val destinationDialog = DestinationBottomSheet()
            destinationDialog.show(parentFragmentManager, destinationDialog.tag)

        }
    }

    private fun selectVehicle() {

        binding.textVehicleType.setOnClickListener {
            val vehicleBottomSheetFragment = VehicleTypeFragment()
            vehicleBottomSheetFragment.show(parentFragmentManager, vehicleBottomSheetFragment.tag)
        }


    }

    private fun selectNumberOfPassenger() {
        binding.passenger.setOnClickListener {
            val passengerBottomSheetFragment = PassengersBottomSheet()
            passengerBottomSheetFragment.show(
                parentFragmentManager,
                passengerBottomSheetFragment.tag
            )
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun showDatePickerDialog() {

        // Picking Date For Departure
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

@SuppressLint("SimpleDateFormat")
fun changeDateFormat(toString: String): String {
    return try {
        val dateFormat = SimpleDateFormat("dd MMM yyyy")
        dateFormat.format(Date())

    } catch (e: Exception) {
        return ""
    }
}