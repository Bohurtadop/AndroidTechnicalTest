package com.bohurtadop.technicaltest.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getTeams(@Url url: String): Response<TeamResponse>

    @GET
    suspend fun getLastEvents(@Url url: String): Response<EventResponse>
}