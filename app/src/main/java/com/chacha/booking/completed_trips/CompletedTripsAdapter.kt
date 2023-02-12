package com.chacha.booking.completed_trips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chacha.booking.databinding.CompletedTripsItemBinding

class CompletedTripsAdapter : ListAdapter<CompletedTrips, CompletedTripsAdapter.MyViewHolder>(
    MyDiffUtilCallback
) {

    object MyDiffUtilCallback : DiffUtil.ItemCallback<CompletedTrips>() {
        override fun areItemsTheSame(oldItem: CompletedTrips, newItem: CompletedTrips): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CompletedTrips, newItem: CompletedTrips): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: CompletedTripsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(completedTrips: CompletedTrips?) {
            binding.numberOfPassenger.text = completedTrips?.name
            binding.textViewBeerType.text = completedTrips?.breweryType
            binding.departurePlace.text = completedTrips?.country
            binding.textViewBeerPhone.text = completedTrips?.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CompletedTripsItemBinding.inflate(
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