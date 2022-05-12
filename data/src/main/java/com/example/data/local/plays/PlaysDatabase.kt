package com.example.data.local.plays

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayEntity::class], version = 1)
abstract class PlaysDatabase: RoomDatabase() {
    abstract val playDao: PlayDao
}