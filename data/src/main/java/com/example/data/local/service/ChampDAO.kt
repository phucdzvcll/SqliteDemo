package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.data.local.response.ChampDBO

@Dao
interface ChampDAO {
    @Query("SELECT * FROM champ")
    suspend fun getAllChamp(): List<ChampDBO>

    @Insert(onConflict = REPLACE)
    suspend fun insertChamps(userDBOS: List<ChampDBO>)

    @Query("DELETE FROM champ")
    suspend fun clearTable()
}