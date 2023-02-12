package com.chacha.booking.bookings.bottom_sheet.departure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chacha.booking.utils.Resource
import com.chacha.booking.data.local.entity.DestinationsEntity
import com.chacha.booking.domain.repository.DepartureStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartureBottomSheetViewModel @Inject constructor(
    private val repository: DepartureStationRepository
) : ViewModel() {
    private val _departure = MutableLiveData<Resource<List<DestinationsEntity>>>()
    val departure: LiveData<Resource<List<DestinationsEntity>>> = _departure

    init {
        getDestination()
    }

    private fun getDestination() {
        _departure.value = Resource.Loading()
        viewModelScope.launch {
            _departure.value = repository.getAllDestination()

        }
    }
}