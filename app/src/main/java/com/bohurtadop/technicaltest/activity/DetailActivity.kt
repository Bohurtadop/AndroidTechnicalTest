package com.bohurtadop.technicaltest.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bohurtadop.technicaltest.adapter.EventAdapter
import com.bohurtadop.technicaltest.api.ApiService
import com.bohurtadop.technicaltest.databinding.ActivityDetailBinding
import com.bohurtadop.technicaltest.item.Event
import com.bohurtadop.technicaltest.item.Team
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var eventAdapter: EventAdapter
    private lateinit var team: Team
    private val events = mutableListOf<Event>()
    private var baseURL = "https://www.thesportsdb.com/api/v1/json/2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        team = intent.getSerializableExtra("TEAM") as Team
        setUpTeamDetail()
        initRecyclerView()
        eventAdapter = EventAdapter(this, events)
        binding.eventList.adapter = eventAdapter
        getLastEventsByTeamId(team.idTeam)
        setUpTeamSocialMediaButtons()
    }

    private fun initRecyclerView(){
        binding.eventList.layoutManager = LinearLayoutManager(this)
    }

    private fun getLastEventsByTeamId(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getLastEvents("eventslast.php?id=${id}")
            val eventResponse = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val eventList: List<Event> = eventResponse?.events ?: emptyList()
                    events.clear()
                    events.addAll(eventList)
                    println(events)
                    eventAdapter.notifyDataSetChanged()
                } else {
                    showError()
                    println("Error getting last events");
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun setUpTeamSocialMediaButtons(){
        binding?.websiteButton?.setOnClickListener {
            val link = Uri.parse("https://" + team?.strWebsite + "/")
            var redirect = Intent(Intent.ACTION_VIEW, link)
            startActivity(redirect)
        }
        binding?.facebookButton?.setOnClickListener {
            val link = Uri.parse("https://" + team?.strFacebook + "/")
            var redirect = Intent(Intent.ACTION_VIEW, link)
            startActivity(redirect)
        }
        binding?.instagramButton?.setOnClickListener {
            val link = Uri.parse("https://" + team?.strInstagram + "/")
            var redirect = Intent(Intent.ACTION_VIEW, link)
            startActivity(redirect)
        }
        binding?.twitterButton?.setOnClickListener {
            val link = Uri.parse("https://" + team?.strTwitter + "/")
            var redirect = Intent(Intent.ACTION_VIEW, link)
            startActivity(redirect)
        }
        binding?.youtubeButton?.setOnClickListener {
            val link = Uri.parse("https://" + team?.strYoutube + "/")
            var redirect = Intent(Intent.ACTION_VIEW, link)
            startActivity(redirect)
        }
    }

    private fun setUpTeamDetail() {
        Picasso.get().load(team?.strTeamBadge).into(binding?.teamBadgeDetail)
        Picasso.get().load(team?.strTeamJersey).into(binding?.teamJerseyDetail)
        binding?.teamFoundationYearDetail?.text = "Foundation year: " + team?.intFormedYear
        binding?.teamDescriptionDetail?.text = team?.strDescriptionEN
        title = team?.strTeam

    }
}