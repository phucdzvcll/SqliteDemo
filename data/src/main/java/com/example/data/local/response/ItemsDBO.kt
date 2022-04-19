package com.example.data.local.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemsDBO(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val description: String?,
    val shadowBonus: String?,
    val shadowPenalty: String?,
    val isShadow: Boolean?,
    val imgUrl: String?,
    val imagePath: String?,
    val elements : Int?
)