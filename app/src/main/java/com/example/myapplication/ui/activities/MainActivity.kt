package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.data.models.LoveResult
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnResult.setOnClickListener {
            App().api.getPercentage(
                key = "5ca609edcemsh37fb5858eaaf930p1c12bejsnfc6d9b8ad6ab",
                host = "love-calculator.p.rapidapi.com",
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            )?.enqueue(object : retrofit2.Callback<LoveResult> {
                override fun onResponse(
                    call: retrofit2.Call<LoveResult>,
                    response: Response<LoveResult>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val loveResult = response.body()
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra("percentage", loveResult?.percentage)
                        intent.putExtra("fname", binding.etFirstName.text.toString())
                        intent.putExtra("sname", loveResult?.sname)
                        intent.putExtra("result", loveResult?.result)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: retrofit2.Call<LoveResult>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}