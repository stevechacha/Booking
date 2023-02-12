package com.chacha.booking.upcoming_trips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chacha.booking.databinding.UpcomingTripsItemBinding


class UpcomingTripsAdapter : ListAdapter<UpcomingTrips, UpcomingTripsAdapter.MyViewHolder>(
    MyDiffUtilCallback
) {

    object MyDiffUtilCallback : DiffUtil.ItemCallback<UpcomingTrips>() {
        override fun areItemsTheSame(oldItem: UpcomingTrips, newItem: UpcomingTrips): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UpcomingTrips, newItem: UpcomingTrips): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: UpcomingTripsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingTrips: UpcomingTrips?) {
            binding.numberOfPassenger.text = upcomingTrips?.name
            binding.textViewBeerType.text = upcomingTrips?.breweryType
            binding.departurePlace.text = upcomingTrips?.country
            binding.textViewBeerPhone.text = upcomingTrips?.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            UpcomingTripsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val beer = getItem(position)
        holder.bind(beer)
    }
}