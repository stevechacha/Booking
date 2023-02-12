package com.chacha.booking.bookings.fragment.mult_city

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chacha.booking.R
import com.chacha.booking.search_results.date_two.SecondDateScreen
import com.chacha.booking.ui.BookingTheme

class MultiCityFragment : Fragment(R.layout.multi_city_fragment) {

    private lateinit var viewModel: MultiCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                BookingTheme {
                    MultiCityScreen()
                }
            }
        }
    }

}

@Composable
fun MultiCityScreen() {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Flight 1")
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 12.dp, end = 12.dp),
                ) {
                    Text(text = "Nairobi")
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Nairobi")

                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Date +\n2024")

            }


        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Flight 1")
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 12.dp, end = 12.dp),
                ) {
                    Text(text = "Nairobi")
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Nairobi")

                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Date +\n2024")

            }


        }
        
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "1\t Passenger")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Economy")

            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Search Flight",
                modifier = Modifier.padding(8.dp)
            )
            
        }
        

    }


}

@Preview
@Composable
fun MultiCityPreview() {
    BookingTheme {
        MultiCityScreen()
    }
}