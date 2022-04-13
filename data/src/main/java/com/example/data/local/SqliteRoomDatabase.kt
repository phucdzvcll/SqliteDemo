package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.response.ChampDBO
import com.example.data.local.service.ChampDAO

@Database(
    entities = [
        ChampDBO::class,
    ],
    version = 2
)

abstract class SqliteRoomDatabase : RoomDatabase() {
    abstract fun champDAO(): ChampDAO

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