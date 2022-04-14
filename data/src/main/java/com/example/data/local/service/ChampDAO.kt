package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ChampDBO

@Dao
interface ChampDAO {
    @Query("SELECT * FROM champ")
     fun getAllChamp(): List<ChampDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertChamps(userDBOS: List<ChampDBO>)

    @Query("DELETE FROM champ")
     fun clearTable()
}