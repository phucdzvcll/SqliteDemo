package com.example.data.local.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "traits")
data class TraitDBO(
    @PrimaryKey
    val traitKey: String,
    val description: String?,
    val innate: String?,
    val name: String?,
    val type: String?,
    val imgUrl: String?,
    val imgPath: String?
)