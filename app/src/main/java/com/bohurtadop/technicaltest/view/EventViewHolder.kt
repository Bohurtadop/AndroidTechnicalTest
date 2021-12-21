package com.bohurtadop.technicaltest.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bohurtadop.technicaltest.item.Event
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.team_item.view.eventDateItem
import kotlinx.android.synthetic.main.team_item.view.eventNameItem

class EventViewHolder (val view:View): RecyclerView.ViewHolder(view) {

    fun render(event: Event){
        view.eventNameItem.text = event.strEvent
        view.eventScoreItem.text =
            event.strHomeTeam + " " + event.intHomeScore + " - " + event.intAwayScore + " " + event.strAwayTeam
        view.eventDateItem.text = event.dateEvent + " " + event.strTime
    }
}