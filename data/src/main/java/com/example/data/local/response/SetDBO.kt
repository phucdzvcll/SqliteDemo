package com.example.data.local.response

import androidx.room.Entity

@Entity(tableName = "sets", primaryKeys = ["style", "traitKey"])
data class SetDBO(
    val traitKey: String,
    val active: String?,
    val qty: Int?,
    val style: String
)