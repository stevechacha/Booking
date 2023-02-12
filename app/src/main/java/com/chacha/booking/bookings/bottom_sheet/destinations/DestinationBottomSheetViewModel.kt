package com.chacha.booking.bookings.bottom_sheet.destinations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chacha.booking.utils.Resource
import com.chacha.booking.data.local.entity.DestinationsEntity
import com.chacha.booking.domain.repository.DestinationStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationBottomSheetViewModel @Inject constructor(
    private val repository: DestinationStationRepository
) : ViewModel() {

    private val _destination = MutableLiveData<Resource<List<DestinationsEntity>>>()
    val destination : LiveData<Resource<List<DestinationsEntity>>> = _destination

    init {
        getDestination()
    }

    private fun getDestination() {
        _destination.value = Resource.Loading()
        viewModelScope.launch {
            _destination.value = repository.getAllDestination()

        }
    }
}