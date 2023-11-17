package com.chacha.presentation.booking.booking_bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.presentation.R
import com.chacha.presentation.booking.BookingUiEvent
import com.chacha.presentation.booking.BookingUiViewModel
import com.chacha.presentation.booking.VehicleType
import com.chacha.presentation.common.components.RideCard
import com.chacha.presentation.settings.ButtonRow


@Composable
fun BookingVehicleTypeBottomSheet(
    onClose:()->Unit
) {
    val bookingUiViewModel: BookingUiViewModel = viewModel()
    val bookingUiState by bookingUiViewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                .safeGesturesPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            VehicleTypeItem(
                vehicleType = VehicleType.BUS,
                onSelected = { vehicleType ->
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.VehicleTypeSelected(vehicleType))
                    onClose()
                },
                selected = bookingUiState.vehicleType.type == VehicleType.BUS.type
            )

            VehicleTypeItem(
                vehicleType = VehicleType.TRAIN,
                onSelected = { vehicleType ->
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.VehicleTypeSelected(vehicleType))
                    onClose()
                },
                selected = bookingUiState.vehicleType.type == VehicleType.TRAIN.type
            )

            VehicleTypeItem(
                vehicleType = VehicleType.FLIGHT,
                onSelected = { vehicleType ->
                    bookingUiViewModel.handleUiEvent(BookingUiEvent.VehicleTypeSelected(vehicleType))
                    onClose()
                },
                selected = bookingUiState.vehicleType.type == VehicleType.FLIGHT.type
            )
            Box(modifier = Modifier.size(40.dp))
        }
    }

}

@Composable
private fun VehicleTypeItem(
    vehicleType: VehicleType,
    selected: Boolean = false,
    onSelected: (VehicleType) -> Unit,
    icon: Int = R.drawable.bus_travel
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(interactionSource, null,
                onClickLabel = vehicleType.type
            ) {
                onSelected(vehicleType)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = icon) ,
                contentDescription = vehicleType.type,
                modifier = Modifier.width(30.dp).height(100.dp)
            )
            Text(text = vehicleType.type)
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = selected,
                onClick = { onSelected(vehicleType) }
            )

        }
    }

}
