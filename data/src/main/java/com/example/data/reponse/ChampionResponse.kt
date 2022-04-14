package com.example.data.reponse

import com.google.gson.annotations.SerializedName

    data class ChampionResponse(
        @SerializedName("cost")
        val cost: Int?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("traits")
        val traits: List<String?>?,
    )

