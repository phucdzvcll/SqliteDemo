package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ChampTraitsDBO

@Dao
interface ChampDAO {
    @Query("SELECT * FROM champ")
    suspend fun getAllChamp(): List<ChampDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChamps(userDBOS: List<ChampDBO>)

    @Query("DELETE FROM champ")
    fun clearTable()

    @Query("SELECT * FROM champ WHERE id = :champId")
    suspend fun getChampById(champId: String): ChampDBO?

    @Query(
        "select c.*, ct2.trait from champ_traits as ct " +
                "inner join champ_traits as ct2 on ct2.trait = ct.trait and ct.champ = :champId "
                +
                "inner join champ as c on c.id = ct2.champ "
    )
    suspend fun getLeagueByChampId(champId: String): List<ChampByTrait>

    data class ChampByTrait(
        val id: String,
        val name: String,
        val imgUrl:String,
        val imagePath:String?,
        val cost: Int,
        val coverUrl: String,
        val coverPath: String?,
        val trait: String,
    )
}