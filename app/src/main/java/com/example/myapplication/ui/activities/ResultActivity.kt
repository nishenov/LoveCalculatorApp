package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.data.models.LoveResult
import com.example.myapplication.databinding.ActivitySecondBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val percentage = intent.getStringExtra("percentage")
        val sname = intent.getStringExtra("sname")
        val fname = intent.getStringExtra("fname")
        val result = intent.getStringExtra("result")
        App().getInstance()?.loveDao()?.insertNote(LoveResult(fname.toString(), sname.toString(), percentage.toString(), result.toString()))

        if (percentage != null && sname != null && fname != null && result != null) {
            binding.tvResult.text = "Совместимость между $fname и $sname: $percentage%. $result"
        }

        binding.iconHistory.setOnClickListener {
            val intent = Intent(this@ResultActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
