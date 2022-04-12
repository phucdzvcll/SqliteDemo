package com.example.data.remote

import com.example.data.reponse.ChampionResponse
import retrofit2.http.*

interface ChampionApiService {
    @GET("champions")
    suspend fun getChampsList(): List<ChampionResponse>
}