package com.bohurtadop.technicaltest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bohurtadop.technicaltest.R
import com.bohurtadop.technicaltest.item.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_item.view.*

class TeamAdapter(private val myContext: Context, private val teams: List<Team>):
    ArrayAdapter<Team>(myContext, 0, teams){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = LayoutInflater.from(myContext).inflate(R.layout.team_item, parent, false)
        val item:Team = teams[position]

        view.eventNameItem.text = item.strTeam
        view.eventDateItem.text = item.strStadium
        Picasso.get().load(item.strTeamBadge).into(view.teamBadgeItem)

        return view
    }
}