package com.chacha.booking.parcel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chacha.booking.R
import com.chacha.booking.ui.BookingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParcelFragment : Fragment(R.layout.parcel_fragment) {
//    private lateinit var viewModel: ParcelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                BookingTheme {
                    val viewModel = viewModel<ParcelViewModel>()
                    val searchText by viewModel.searchText.collectAsState()
                    val persons by viewModel.persons.collectAsState()
                    val isSearching by viewModel.isSearching.collectAsState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        TextField(
                            value = searchText,
                            onValueChange = viewModel::onSearchTextChange,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Search") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (isSearching) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                items(persons) { person ->
                                    Text(
                                        text = "${person.firstName} ${person.lastName}",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}