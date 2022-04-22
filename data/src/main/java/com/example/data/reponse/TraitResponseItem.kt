package com.example.data.reponse


import com.google.gson.annotations.SerializedName

data class TraitResponseItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("innate")
    val innate: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sets")
    val sets: List<Set>?,
    @SerializedName("type")
    val type: String?
) {
    data class Set(
        @SerializedName("active")
        val active: String?,
        @SerializedName("max")
        val max: Int?,
        @SerializedName("min")
        val min: Int?,
        @SerializedName("style")
        val style: String?
    )
}