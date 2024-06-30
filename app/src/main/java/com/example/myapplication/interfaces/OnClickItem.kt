package com.example.myapplication.interfaces

import com.example.myapplication.data.models.LoveResult

interface OnClickItem {
    fun onLongClick(loveResult: LoveResult)
}