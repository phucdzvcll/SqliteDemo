package com.example.data.local.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champ")
data class ChampDBO(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgUrl:String,
    val imagePath:String?,
    val cost: Int
){
}
