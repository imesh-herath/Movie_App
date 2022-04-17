package com.example.w1809809_cw2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version=1)

abstract class MovieDB:RoomDatabase() {
    abstract fun movieDao(): MovieDao
}