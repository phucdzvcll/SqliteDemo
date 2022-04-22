package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.response.*
import com.example.data.local.service.*

@Database(
    entities = [
        ChampDBO::class,
        ChampTraitsDBO::class,
        ChampItemsDBO::class,
        ItemsDBO::class,
        TraitDBO::class,
        SetDBO::class,
    ],
    version = 3
)

abstract class SqliteRoomDatabase : RoomDatabase() {
    abstract fun champDAO(): ChampDAO
    abstract fun champTraitsDAO(): ChampTraitsDAO
    abstract fun ChampItemsDAO(): ChampItemsDAO
    abstract fun ItemsDAO(): ItemsDAO
    abstract fun SetDAO(): SetDAO
    abstract fun TraitsDAO(): TraitsDAO

    companion object {

        private var INSTANCE: SqliteRoomDatabase? = null

        fun getInstance(context: Context): SqliteRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    SqliteRoomDatabase::class.java,
                    "sqlite demo"
                ).build()
            }

            return INSTANCE!!
        }
    }

}