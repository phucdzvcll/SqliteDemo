package com.example.data.local.response

import androidx.room.Entity

@Entity(tableName = "champ_traits", primaryKeys = ["champ","trait"])
data class ChampTraitsDBO(
    val champ : String,
    val trait : String,
)
