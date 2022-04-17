package com.example.data.local.response

import androidx.room.Entity

@Entity(tableName = "champ_items", primaryKeys = ["champ","item"])
data class ChampItemsDBO(
    val champ : String,
    val item : String,
)
