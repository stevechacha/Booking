package com.chacha.presentation.booking.booking_component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chacha.presentation.R
import com.chacha.presentation.common.theme.BookingTheme

enum class PeopleUserInputAnimationState { Valid, Invalid }

class PeopleUserInputState {
    var people by mutableStateOf(1)
        private set

    val animationState: MutableTransitionState<PeopleUserInputAnimationState> =
        MutableTransitionState(PeopleUserInputAnimationState.Valid)

    fun addPerson() {
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState =
            if (people > MAX_PEOPLE) PeopleUserInputAnimationState.Invalid
            else PeopleUserInputAnimationState.Valid

        if (animationState.currentState != newState) animationState.targetState = newState
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PeopleUserInput(
    titleSuffix: String = "",
    onPeopleChanged: (Int) -> Unit,
    peopleState: PeopleUserInputState = remember { PeopleUserInputState() }
) {
    Column {
        val transitionState = remember { peopleState.animationState }
        val tint = tintPeopleUserInput(transitionState)

        val people = peopleState.people
        CraneUserInput(
            text = pluralStringResource(
                id = R.plurals.number_adults_selected,
                count = people,
                people,
                titleSuffix
            ),
            vectorImageId = R.drawable.ic_person,
            tint = tint.value,
            onClick = {
                peopleState.addPerson()
                onPeopleChanged(peopleState.people)
            }
        )
        if (transitionState.targetState == PeopleUserInputAnimationState.Invalid) {
            Text(
                text = stringResource(
                    id = R.string.error_max_people,
                    MAX_PEOPLE
                ),
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary)
            )
        }
    }
}

val MAX_PEOPLE = 20

@Composable
fun FromDestination() {
    CraneUserInput(text = "Seoul, South Korea", vectorImageId = R.drawable.ic_location)
}

@Composable
fun ToDestinationUserInput(destination: String? = null,onToDestinationChanged: (String) -> Unit) {
    CraneEditableUserInput(
        hint = stringResource(R.string.select_destination_hint),
        caption = stringResource(R.string.select_destination_to_caption),
        vectorImageId = R.drawable.ic_plane,
        onInputChanged = onToDestinationChanged
    )
}

@Composable
fun DatesUserInput(datesSelected: String, onDateSelectionClicked: () -> Unit) {
    CraneUserInput(
        onClick = onDateSelectionClicked,
        caption = if (datesSelected.isEmpty()) stringResource(R.string.select_dates) else null,
        text = datesSelected,
        vectorImageId = R.drawable.ic_calendar
    )
}

@Composable
fun FromUserInput(destinationSelected: String, onDestinationSelectionClicked: (String) -> Unit) {
    CraneUserInput(
        onClick = {
            onDestinationSelectionClicked(destinationSelected)
        },
        caption = if (destinationSelected.isEmpty()) stringResource(R.string.select_departure_hint) else null,
        text = destinationSelected,
    )
}
@Composable
fun ToUserInput(destinationSelected: String, onDestinationSelectionClicked: (String) -> Unit) {
    CraneUserInput(
        onClick = {
            onDestinationSelectionClicked(destinationSelected)
        },
        caption = if (destinationSelected.isEmpty()) stringResource(R.string.select_destination_hint) else null,
        text = destinationSelected,
    )
}

@Composable
private fun tintPeopleUserInput(
    transitionState: MutableTransitionState<PeopleUserInputAnimationState>
): State<Color> {
    val validColor = MaterialTheme.colors.onSurface
    val invalidColor = MaterialTheme.colors.secondary

    val transition = updateTransition(transitionState)
    return transition.animateColor(
        transitionSpec = { tween(durationMillis = 300) }
    ) {
        if (it == PeopleUserInputAnimationState.Valid) validColor else invalidColor
    }
}

@Preview
@Composable
fun PeopleUserInputPreview() {
    BookingTheme {
        PeopleUserInput(onPeopleChanged = {})
    }
}