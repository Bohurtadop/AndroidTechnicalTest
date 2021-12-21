package com.bohurtadop.technicaltest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bohurtadop.technicaltest.R
import com.bohurtadop.technicaltest.adapter.TeamAdapter
import com.bohurtadop.technicaltest.api.ApiService
import com.bohurtadop.technicaltest.databinding.ActivityMainBinding
import com.bohurtadop.technicaltest.item.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var teamAdapter: TeamAdapter
    private val teams = mutableListOf<Team>()
    private var league = "Spanish%20La%20Liga"
    private var baseURL = "https://www.thesportsdb.com/api/v1/json/2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)
        initListView()
        searchByLeague(league)
    }

    override fun onBackPressed() {
        return;
    }

    private fun initListView() {
        teamAdapter = TeamAdapter(this, teams)
        mainBinding.teamList.adapter = teamAdapter
        mainBinding.teamList.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("TEAM", teams[i])
            startActivity(intent)
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchByLeague(league: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getTeams("search_all_teams.php?l=${league}")
            val teamResponse = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val teamList: List<Team> = teamResponse?.teams ?: emptyList()
                    teams.clear()
                    teams.addAll(teamList)
                    teamAdapter.notifyDataSetChanged()
                } else {
                    showError()
                    println("Error getting team information API");
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}