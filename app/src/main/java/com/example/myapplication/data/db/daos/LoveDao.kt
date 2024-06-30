package com.example.myapplication.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.models.LoveResult

@Dao
interface LoveDao {
    @Query("SELECT * FROM loveResults ORDER BY fname ASC")
    fun getAll(): LiveData<List<LoveResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(loveResult: LoveResult)

    @Delete
    fun deleteNote(loveResult: LoveResult)

    @Query("SELECT * FROM loveResults WHERE id = :id")
    fun getNote(id: Int): LoveResult

    @Update
    fun updateNote(loveResult: LoveResult)
}