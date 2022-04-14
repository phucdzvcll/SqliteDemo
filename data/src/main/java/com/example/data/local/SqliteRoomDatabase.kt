package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.response.ChampDBO
import com.example.data.local.response.ChampTraitsDBO
import com.example.data.local.service.ChampDAO
import com.example.data.local.service.ChampTraitsDAO

@Database(
    entities = [
        ChampDBO::class,
        ChampTraitsDBO::class,
    ],
    version = 3
)

abstract class SqliteRoomDatabase : RoomDatabase() {
    abstract fun champDAO(): ChampDAO
    abstract fun champTraitsDAO(): ChampTraitsDAO

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