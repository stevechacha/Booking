package com.chacha.presentation.booking.booking_destination_list

import android.annotation.SuppressLint
import java.time.LocalDateTime
import androidx.annotation.ColorRes
import java.time.YearMonth
import com.chacha.presentation.R
import java.time.MonthDay
import java.time.format.DateTimeFormatter

private typealias Airport = BusBooking.Airport

data class BusBooking(
    val time: LocalDateTime,
    val departure: Airport,
    val destination: Airport,
    @ColorRes val color: Int,
) {
    data class Airport(val city: String, val code: String)
}

@SuppressLint("NewApi")
fun generateBusBookings(): List<BusBooking> = buildList {
    val currentMonth = YearMonth.now()
    val currentDate = MonthDay.now()

    currentMonth.atDay(17).also { date ->
        add(
            BusBooking(
                date.atTime(14, 0),
                Airport("Lagos", "LOS"),
                Airport("Abuja", "ABV"),
                R.color.blue_800,
            ),
        )
        add(
            BusBooking(
                date.atTime(21, 30),
                Airport("Enugu", "ENU"),
                Airport("Owerri", "QOW"),
                R.color.red_800,
            ),
        )
    }

    currentMonth.atDay(22).also { date ->
        add(
            BusBooking(
                date.atTime(13, 20),
                Airport("Ibadan", "IBA"),
                Airport("Benin", "BNI"),
                R.color.brown_700,
            ),
        )
        add(
            BusBooking(
                date.atTime(17, 40),
                Airport("Sokoto", "SKO"),
                Airport("Ilorin", "ILR"),
                R.color.blue_grey_700,
            ),
        )
    }

    currentMonth.atDay(13).also { date ->
        add(
            BusBooking(
                date.atTime(20, 0),
                Airport("Makurdi", "MDI"),
                Airport("Calabar", "CBQ"),
                R.color.teal_700,
            ),
        )
    }

    currentMonth.atDay(12).also { date ->
        add(
            BusBooking(
                date.atTime(18, 15),
                Airport("Kaduna", "KAD"),
                Airport("Jos", "JOS"),
                R.color.cyan_700,
            ),
        )
    }

    currentMonth.plusMonths(11).atDay(13).also { date ->
        add(
            BusBooking(
                date.atTime(7, 30),
                Airport("Kano", "KAN"),
                Airport("Akure", "AKR"),
                R.color.pink_700,
            ),
        )
        add(
            BusBooking(
                date.atTime(10, 50),
                Airport("Minna", "MXJ"),
                Airport("Zaria", "ZAR"),
                R.color.green_700,
            ),
        )
    }

    currentMonth.minusMonths(11).atDay(9).also { date ->
        add(
            BusBooking(
                date.atTime(20, 15),
                Airport("Asaba", "ABB"),
                Airport("Port Harcourt", "PHC"),
                R.color.orange_800,
            ),
        )
    }
}

@SuppressLint("NewApi")
val flightDateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("EEE'\n'dd MMM'\n'HH:mm")

/*
data class Flight(
    val date: LocalDateTime,
    val time: String,
    val departure: BusStation,
    val destination: BusStation,
    val seat: String,
    val fare: Double,
    val availableSeats: Int,
    val boardingStation: String,
    val droppingStation: String,
    val passengers: List<Passenger>
)

data class BusStation(val city: String, val code: String)

data class Passenger(
    val name: String,
    val seatNumber: String,
    val gender: Gender,
    val nationality: String,
    val idNumber: String,
    val mobileNumber: String
)

enum class Gender {
    MALE,
    FEMALE
}

@SuppressLint("NewApi")
fun generateBusBookings(): List<Flight> = buildList {
    val currentMonth = LocalDateTime.now().withHour(0).withMinute(0)

    currentMonth.plusDays(1).also { date ->
        add(
            Flight(
                date,
                "10:00 AM",
                BusStation("City A", "CA"),
                BusStation("City B", "CB"),
                "A101",
                25.0,
                40,
                "Station X",
                "Station Y",
                listOf(
                    Passenger("Passenger 1", "A101", Gender.MALE, "Country", "ID001", "1234567890"),
                    Passenger("Passenger 2", "A102", Gender.FEMALE, "Country", "ID002", "9876543210")
                )
            )
        )
        add(
            Flight(
                date,
                "02:30 PM",
                BusStation("City B", "CB"),
                BusStation("City C", "CC"),
                "B202",
                30.0,
                30,
                "Station Y",
                "Station Z",
                listOf(
                    Passenger("Passenger 3", "B202", Gender.FEMALE, "Country", "ID003", "5555555555")
                )
            )
        )
    }
    // Add more bookings for different dates, times, and routes following a similar pattern
}*/
