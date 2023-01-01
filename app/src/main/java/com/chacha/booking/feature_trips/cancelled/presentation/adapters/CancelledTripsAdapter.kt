package com.chacha.booking.feature_trips.cancelled.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chacha.booking.databinding.CancelledTripsItemBinding
import com.chacha.booking.feature_trips.cancelled.domain.model.CancelledTrips


class CancelledTripsAdapter : ListAdapter<CancelledTrips, CancelledTripsAdapter.MyViewHolder>(MyDiffUtilCallback) {

    object MyDiffUtilCallback : DiffUtil.ItemCallback<CancelledTrips>() {
        override fun areItemsTheSame(oldItem: CancelledTrips, newItem: CancelledTrips): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CancelledTrips, newItem: CancelledTrips): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: CancelledTripsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cancelledTrips: CancelledTrips?) {
            binding.numberOfPassenger.text = cancelledTrips?.name
            binding.textViewBeerType.text = cancelledTrips?.breweryType
            binding.departurePlace.text = cancelledTrips?.country
            binding.textViewBeerPhone.text = cancelledTrips?.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CancelledTripsItemBinding.inflate(
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