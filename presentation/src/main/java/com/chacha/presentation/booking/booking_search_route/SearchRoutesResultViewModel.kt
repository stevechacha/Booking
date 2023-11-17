package com.chacha.presentation.booking.booking_search_route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class SearchRoutesResultViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    var searchRoutesResults by mutableStateOf<SearchRoutesResults>(SearchRoutesResults())
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val searchResultAdapter = moshi.adapter(SearchRoutesResults::class.java)

    init {
        savedStateHandle.get<String>("searchResult")?.let {searchResultJson->
            searchResultAdapter.fromJson(searchResultJson)?.let { newSearchJson->
                searchRoutesResults = newSearchJson
            }
        }
    }

}