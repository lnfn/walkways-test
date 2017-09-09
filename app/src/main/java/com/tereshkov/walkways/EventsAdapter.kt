package com.tereshkov.walkways

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tereshkov.walkways.data.BusSheduleEvents
import kotlinx.android.synthetic.main.item_bus_event.view.*

class EventsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<BusSheduleEvents>()

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(data[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: BusSheduleEvents) {
            itemView.busNumberTextView.text = "Рейс №${item.number_bus}"
            itemView.stationTextView.text = "откуда: ${item.from_station}, Олимпийская\nкуда: ${item.to_station}, Сидоренко"
        }
    }

    fun refreshData(data: List<BusSheduleEvents>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}