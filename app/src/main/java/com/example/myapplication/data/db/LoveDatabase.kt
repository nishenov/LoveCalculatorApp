package com.example.myapplication.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.db.daos.LoveDao
import com.example.myapplication.data.models.LoveResult

@Database(entities = [LoveResult::class], version = 1)
abstract class LoveDatabase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}
