package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ChampItemsDBO

@Dao
interface ChampItemsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampsItems(userDBOS: List<ChampItemsDBO>)

    @Query("DELETE FROM champ_items")
    fun clearTable()
}