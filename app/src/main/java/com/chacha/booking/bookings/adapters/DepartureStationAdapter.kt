package com.chacha.booking.bookings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chacha.booking.databinding.DestinationStationItemBinding
import com.chacha.booking.data.local.entity.DestinationsEntity

class DepartureStationAdapter : ListAdapter<DestinationsEntity, DepartureStationAdapter.MyViewHolder>(
    MyDiffUtilCallback
) {

    object MyDiffUtilCallback : DiffUtil.ItemCallback<DestinationsEntity>() {
        override fun areItemsTheSame(oldItem: DestinationsEntity, newItem: DestinationsEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DestinationsEntity, newItem: DestinationsEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: DestinationStationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(destinations: DestinationsEntity?) {
            binding.departureCity.text = destinations?.fromCity
            binding.departureCityStation.text = destinations?.fromCityStation

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DestinationStationItemBinding.inflate(
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