package com.example.testempresa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarTable::class],
    version = 2,
    exportSchema = false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun getInstance() : CarDao
}