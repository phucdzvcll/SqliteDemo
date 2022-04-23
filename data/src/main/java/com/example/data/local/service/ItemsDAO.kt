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
    suspend fun getAllItems(): List<ItemsDBO>

    @Query("DELETE FROM items")
    fun clearTable()

    @Query(
        "Select * from items as item" +
                " inner join champ_items as champ on champ.champ = :champId " +
                "and champ.item = item.name"
    )
    suspend fun getItemByChampId(champId: String): List<ItemsDBO>

    @Query(
        "select i.*, i2.imgUrl as element1Url, i2.imagePath as element1Path, " +
                " i3.imgUrl as element2Url, i3.imagePath as element2Path from items as i " +
                "left join items as i2 on i.element1 = i2.id " +
                "left join items as i3 on i.element2 = i3.id " +
                "where i.id = :itemId"
    )
    suspend fun getDetailItemById(itemId: Int) : ItemDetailDBO

    data class ItemDetailDBO(
        val id: Int,
        val name: String?,
        val description: String?,
        val shadowBonus: String?,
        val shadowPenalty: String?,
        val isShadow: Boolean?,
        val isSpecial: Boolean?,
        val imgUrl: String?,
        val element1 : Int?,
        val element2 : Int?,
        val imagePath: String?,
        val element1Url: String?,
        val element1Path: String?,
        val element2Url: String?,
        val element2Path: String?,
        val isElement: Boolean?
    )
}