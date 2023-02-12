package com.chacha.booking.search_results.adapters

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chacha.booking.onboarding.FourthFragment
import com.chacha.booking.onboarding.SecondFragment
import com.chacha.booking.search_results.date_three.ThirdDateFragment
import com.chacha.booking.search_results.date_two.SecondDateFragment
import com.chacha.booking.search_results.first_date.FirstDateFragment
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun getCurrentDateInEnglish(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateTwoDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 1)
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateThreeDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 2)
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateFourthDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 3)
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDateFifthDaysAhead(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, 4)
    val dateFormat = SimpleDateFormat("dd MMM")
    return dateFormat.format(calendar.time)
}

// Get the current date
val currentDate = Calendar.getInstance().time

// Create a SimpleDateFormat object to specify the format of the date string
val dateFormat = SimpleDateFormat("dd-MM", Locale.getDefault())

// Format the date using the SimpleDateFormat object and save it as a string

val dateString: String = dateFormat.format(currentDate)
private val TAB_TITLES = arrayOf(
    getCurrentDateInEnglish(),
    getDateTwoDaysAhead(),
    getDateThreeDaysAhead(),
    getDateFourthDaysAhead() ,
    getDateFifthDaysAhead()

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class BookingSearchResultPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> FirstDateFragment()
            1 -> SecondDateFragment()
            2 -> ThirdDateFragment()
            3 -> FourthFragment()
            4 -> SecondFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position].toString()
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 5
    }
}