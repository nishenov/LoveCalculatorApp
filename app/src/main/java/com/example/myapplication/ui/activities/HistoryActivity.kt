package com.example.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.data.models.LoveResult
import com.example.myapplication.databinding.ActivityHistoryBinding
import com.example.myapplication.interfaces.OnClickItem
import com.example.myapplication.ui.adapters.HistoryAdapter

class HistoryActivity : AppCompatActivity(), OnClickItem {
    private val binding: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }
    private val historyAdapter = HistoryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvHistory.adapter = historyAdapter
        App().getInstance()?.loveDao()?.getAll()?.observe(this){
            historyAdapter.submitList(it)
        }
    }

    override fun onLongClick(loveResult: LoveResult) {
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Do you want to delete it?")
            setPositiveButton("Yes"){dialog, which ->
                App().getInstance()?.loveDao()?.deleteNote(loveResult)
            }
            setNegativeButton("Нет"){dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
        }
    }
