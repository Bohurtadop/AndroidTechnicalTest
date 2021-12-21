package com.bohurtadop.technicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bohurtadop.technicaltest.R
import com.bohurtadop.technicaltest.item.Event
import com.bohurtadop.technicaltest.view.EventViewHolder
import kotlinx.android.synthetic.main.event_item.view.*

class EventAdapter(private val myContext: Context, private val events: List<Event>) :
    RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item: Event = events[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = events.size
}