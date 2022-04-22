package com.example.data.remote

import com.example.data.reponse.ChampionResponse
import com.example.data.reponse.ItemResponse
import com.example.data.reponse.TraitResponseItem
import retrofit2.http.*

interface SyncDataApiService {
    @GET("champions")
    suspend fun getChampsList(): List<ChampionResponse>
    @GET("items")
    suspend fun getItemsList():List<ItemResponse>
    @GET("traits")
    suspend fun getTraitsList():List<TraitResponseItem>
}