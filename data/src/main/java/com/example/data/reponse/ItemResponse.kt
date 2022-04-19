package com.example.data.reponse

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isElement")
    val isElement: Boolean?,
    @SerializedName("isShadow")
    val isShadow: Boolean?,
    @SerializedName("isSpecial")
    val isSpecial: Boolean?,
    @SerializedName("isUnique")
    val isUnique: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("shadowBonus")
    val shadowBonus: String?,
    @SerializedName("shadowPenalty")
    val shadowPenalty: String?
)