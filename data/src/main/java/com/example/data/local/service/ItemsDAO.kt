package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ChampItemsDBO
import com.example.data.local.response.ChampTraitsDBO
import com.example.data.local.response.ItemsDBO

@Dao
interface ItemsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems(userDBOS: List<ItemsDBO>)


    @Query("SELECT * FROM items")
    fun getAllItems(): List<ItemsDBO>

    @Query("DELETE FROM items")
    fun clearTable()
}