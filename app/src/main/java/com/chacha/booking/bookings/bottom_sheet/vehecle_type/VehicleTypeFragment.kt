package com.chacha.booking.bookings.bottom_sheet.vehecle_type

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import com.chacha.booking.R
import com.chacha.booking.utils.viewBinding
import com.chacha.booking.databinding.VehicleTypeFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleTypeFragment : BottomSheetDialogFragment(R.layout.vehicle_type_fragment) {
    private val viewModel: VehicleTypeViewModel by viewModels()
    private val binding by viewBinding ( VehicleTypeFragmentBinding::bind )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        pickUpVehicle()
    }

    private fun pickUpVehicle() {
       /* binding.apply {

            val checkedRadioButtonId = radioGroup.checkedRadioButtonId // Returns View.NO_ID if nothing is checked.
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.shuttleRadioButton) Toast.makeText(requireContext(),"Shuttle Selected", Toast.LENGTH_SHORT).show()

                if (checkedId == R.id.busRadioButton) Toast.makeText(requireContext(),"Bus Selected Selected", Toast.LENGTH_SHORT).show()
                // Responds to child RadioButton checked/unchecked
            }


*/

    }

    private fun setUpObservers() {
        val radioGroup: RadioGroup = binding.radioGroup
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.shuttleRadioButton -> {

                    // handle selection of the bus radio button
                }
                R.id.busRadioButton -> {
                    // handle selection of the train radio button
                }
               /* R.id.radio_flight -> {
                    // handle selection of the flight radio button
                }*/
            }
        }

    }

}