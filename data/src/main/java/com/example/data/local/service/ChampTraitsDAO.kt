package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ChampTraitsDBO

@Dao
interface ChampTraitsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampsTraits(userDBOS: List<ChampTraitsDBO>)

    @Query("DELETE FROM champ_traits")
    fun clearTable()
}