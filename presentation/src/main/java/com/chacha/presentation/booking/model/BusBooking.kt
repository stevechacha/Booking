package com.chacha.presentation.booking.model

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.MonthDay
import java.time.format.DateTimeFormatter

private typealias Airport = BusBooking.Airport
private typealias Passenger = BusBooking.Passenger

data class BusBooking(
    val bookingId: String,
    val time: LocalDateTime,
    val departureCity: Airport,
    val destinationCity: Airport,
    val boardingTown: String? = null,
    val droppingTown: String? = null,
    val availableSeats: Int,
    val totalFare: Double,
    val paymentStatus: Boolean,
    val bookingDate: String? = null,
    val busCompany: String? = null,
    val busNumber: String? = null,
    val seatNumber: String? = null,
    val passengers: List<Passenger>,
    val departureDate: String?  = null,
    val returnDate: String? = null,
    val totalSeats: Int,
    val bookedSeats: Int,
    val fare: Double = 1500.0


    ) {
    data class Airport(val city: String, val code: String)

    data class Passenger(
        val name: String,
        val gender: String,
        val age: Int,
        val nationality: String,
        val mobileNumber: String,
        val seatNumber: String,
        val idNumber: String,

        )



    fun calculateTotalFare(): Double {
        return fare * passengers.size
    }


}




@SuppressLint("NewApi")
fun generateBusBookings(): List<BusBooking> = buildList {
    val currentMonth = YearMonth.now()
    val currentDate = MonthDay.now()

    currentMonth.atDay(17).also { date ->
        add(
            BusBooking(
                bookingId = "000",
                date.atTime(14, 0),
                Airport("Lagos", "LOS"),
                Airport("Abuja", "ABV"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    Passenger(
                        "John Doe",
                        "Male",
                        30,
                        "Nigerian",
                        "1234567890",
                        seatNumber = "A1",
                        "12345"
                    ),
                    Passenger(
                        "Jane Doe",
                        "Female",
                        25,
                        "Nigerian",
                        "9876543210",
                        seatNumber = "A2",
                        "1111111"
                    )
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
        add(
            BusBooking(
                bookingId = "001",
                date.atTime(21, 30),
                Airport("Enugu", "ENU"),
                Airport("Owerri", "QOW"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }

    currentMonth.atDay(22).also { date ->
        add(
            BusBooking(
                bookingId = "000",
                date.atTime(13, 20),
                Airport("Ibadan", "IBA"),
                Airport("Benin", "BNI"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
        add(
            BusBooking(
                bookingId = "000",

                date.atTime(17, 40),
                Airport("Sokoto", "SKO"),
                Airport("Ilorin", "ILR"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }

    currentMonth.atDay(13).also { date ->
        add(
            BusBooking(
                bookingId = "000",

                date.atTime(20, 0),
                Airport("Makurdi", "MDI"),
                Airport("Calabar", "CBQ"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }

    currentMonth.atDay(12).also { date ->
        add(
            BusBooking(
                bookingId = "000",

                date.atTime(18, 15),
                Airport("Kaduna", "KAD"),
                Airport("Jos", "JOS"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }

    currentMonth.plusMonths(11).atDay(13).also { date ->
        add(
            BusBooking(
                bookingId = "000",

                date.atTime(7, 30),
                Airport("Kano", "KAN"),
                Airport("Akure", "AKR"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
        add(
            BusBooking(
                bookingId = "000",

                date.atTime(10, 50),
                Airport("Minna", "MXJ"),
                Airport("Zaria", "ZAR"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }

    currentMonth.minusMonths(11).atDay(9).also { date ->
        add(
            BusBooking(
                bookingId = "000",
                date.atTime(20, 15),
                Airport("Asaba", "ABB"),
                Airport("Port Harcourt", "PHC"),
                totalSeats = 40,
                bookedSeats = 20,
                passengers = listOf(
                    BusBooking.Passenger("John Doe", "Male", 30, "Nigerian", "1234567890", seatNumber = "A1","12345"),
                    BusBooking.Passenger("Jane Doe", "Female", 25, "Nigerian", "9876543210", seatNumber = "A2","1111111")
                    // Add more passengers with seat numbers
                ),
                availableSeats = 20,
                totalFare = 1500.0,
                paymentStatus = true,
            ),
        )
    }
}

@SuppressLint("NewApi")
val flightDateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("EEE'\t'dd MMM'\t'HH:mm")
@SuppressLint("NewApi")
val flightTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

enum class Gender(val type: String) {
    MALE(type = "Male"),
    FEMALE(type = "Female"),
}




data class SeatDetailsEntry(
    val name: String,
    val idNumber: String,
    val gender: String,
    val mobileNumber: String,
    val nationality: String = "Kenyan"
)

data class SeatDetails(
    val seatIndex: Int,
    val seatLabel: String,
    val details: SeatDetailsEntry
)



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
