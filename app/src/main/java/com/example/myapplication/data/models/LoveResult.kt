package com.example.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "loveResults")
class LoveResult (
    @SerializedName("fname")
    val fname: String,
    @SerializedName("sname")
    val sname: String,
    val percentage: String,
    val result: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}