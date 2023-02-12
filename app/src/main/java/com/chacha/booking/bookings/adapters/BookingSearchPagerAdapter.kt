package com.chacha.booking.bookings.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.chacha.booking.R
import com.chacha.booking.bookings.fragment.mult_city.MultiCityFragment
import com.chacha.booking.bookings.fragment.one_way.OneWayFragment
import com.chacha.booking.bookings.fragment.returns.ReturnsFragment


private val TAB_TITLES = arrayOf(
    R.string.one_way,
    R.string.returns,
    R.string.multi_city

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class BookingSearchPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> OneWayFragment()
            1 -> ReturnsFragment()
            2 -> MultiCityFragment()
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