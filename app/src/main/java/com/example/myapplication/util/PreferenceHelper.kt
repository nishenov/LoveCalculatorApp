package com.example.myapplication.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context) : PreferenceHelper{
        sharedPreference = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        return this
    }

    var isOnBoardShow: Boolean
        get() = sharedPreference.getBoolean("board", false)
        set(value) = sharedPreference.edit().putBoolean("board", value)!!.apply()
}