package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ItemsDBO
import com.example.data.local.response.SetDBO
import com.example.data.local.response.TraitDBO

@Dao
interface TraitsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraits(userDBOS: List<TraitDBO>)

    @Query("select * from traits")
    fun getAllTraits(): List<TraitDBO>

    @Query("DELETE FROM traits")
    fun clearTable()

    @Query(
        "Select * from traits as trait" +
                " inner join champ_traits as champ on champ.champ = :champId " +
                "and champ.trait = trait.traitKey"
    )
    suspend fun getTraitsByChampId(champId: String) : List<TraitDBO>
}