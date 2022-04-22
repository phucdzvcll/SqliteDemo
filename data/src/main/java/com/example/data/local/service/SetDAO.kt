package com.example.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.response.ItemsDBO
import com.example.data.local.response.SetDBO

@Dao
interface SetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSets(userDBOS: List<SetDBO>)


    @Query("SELECT * FROM sets")
    fun getSets(): List<SetDBO>

    @Query("DELETE FROM sets")
    fun clearTable()

    @Query("select s.* from sets as s " +
            "inner join traits as t on t.traitKey = s.traitKey " +
            "inner join champ_traits as c on c.trait = t.traitKey " +
            "where c.champ = :champId")
    suspend fun getSetByChampId(champId: String) : List<SetDBO>
}