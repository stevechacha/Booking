package com.chacha.booking.feature_trips.mytrips.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chacha.booking.R
import com.chacha.booking.feature_trips.cancelled.presentation.fragment.CancelledFragment
import com.chacha.booking.feature_trips.completed.presentation.fragment.CompletedFragment
import com.chacha.booking.feature_trips.upcoming.presentation.fragment.UpcomingFragment

private val TAB_TITLES = arrayOf(
    R.string.upcoming_trip,
    R.string.completed_trips,
    R.string.cancelled_trips
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class MyTripsAdapterPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> UpcomingFragment()
            1 -> CompletedFragment()
            2 -> CancelledFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}