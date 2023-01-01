package com.chacha.booking.feature_bookings.presentation.fragment.returns.presentation.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chacha.booking.R
import com.chacha.booking.core.utils.observeEvent
import com.chacha.booking.core.utils.viewBinding
import com.chacha.booking.databinding.ReturnsFragmentBinding
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.departure.DepartureBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.destinations.DestinationBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.passengers.PassengersBottomSheet
import com.chacha.booking.feature_bookings.presentation.bottom_sheet.vehecle_type.VehicleTypeFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ReturnsFragment : Fragment(R.layout.returns_fragment) {
    private val binding by viewBinding(ReturnsFragmentBinding::bind)
    private val viewModel: ReturnsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setUpObservers()
        currentDate()
        returnDate()
        destinationStation()
        departureStation()
        passengerBottomSheetDialog()
        vehicleTypeBottomSheetDialog()

/*
        showBottomSheet()
*/
//        dateDialog()


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

    @SuppressLint("SimpleDateFormat")
    /* private fun dateDialog() {

         binding.apply {
             textViewDepartureDate.setOnClickListener {
                 val datePicker =
                     MaterialDatePicker.Builder.datePicker()
                         .setTitleText("Select date")
                         .build()

                 val date =datePicker.show(parentFragmentManager, datePicker.tag.toString())

                 date.toString()
             }
         }
     }*/

    private fun vehicleTypeBottomSheetDialog() {
        binding.apply {
            textVehicleType.setOnClickListener {
                val vehicleBottomSheetFragment = VehicleTypeFragment()
                vehicleBottomSheetFragment.show(parentFragmentManager, vehicleBottomSheetFragment.tag)
            }

        }

    }

    private fun passengerBottomSheetDialog() {
        binding.apply {
            numberOfPassenger.setOnClickListener {
                val bottomSheetFragment = PassengersBottomSheet()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            }
        }
    }



    /* private fun showBottomSheet() {
         val bottomSheetFragment = PassengerBottomSheetFragment()
         bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)

     }*/

    private fun returnDate() {
        val dateAfter3Days = binding.textViewReturnDate
        val date3 = getDateThreeDaysAhead()
        dateAfter3Days.text = date3
        binding.textViewReturnDate.setOnClickListener {
            showDatePickerDialog()
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun currentDate() {
        val datePicker = binding.textViewDepartureDate
        val currentDate = getCurrentDateInEnglish()
        datePicker.text = currentDate
        // Set the date range and selection mode
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

        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                // Convert the selected date to a Date object
                calendar.timeInMillis = System.currentTimeMillis()
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
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
                ReturnsUIState.Loading -> {
                    //renderLoading()
                }
                is ReturnsUIState.Error -> {
                    //renderError()
//                    renderError(it.message)
                }
                is ReturnsUIState.Message ->{
//                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is ReturnsActions.Navigate -> findNavController().navigate(it.destination)
//                is ReturnsActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

    /*  private fun showDialog(bottomSheetDialogFragment: BottomSheetDialogFragment) {
          bottomSheetDialogFragment.show(parentFragmentManager,bottomSheetDialogFragment.tag)

      }*/

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