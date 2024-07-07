package com.example.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.data.models.LoveResult
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragments.OnBoardFragment
import com.example.myapplication.util.PreferenceHelper
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var sharedPreferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sharedPreferences = PreferenceHelper().unit(this)
        if (!sharedPreferences.isOnBoardShow){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, OnBoardFragment())
                .addToBackStack(null).commit()
            sharedPreferences.isOnBoardShow =true
        }

        binding.btnResult.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            binding.progressBar.visibility = View.VISIBLE
            binding.etFirstName.visibility = View.GONE
            binding.etSecondName.visibility = View.GONE
            binding.btnResult.visibility = View.GONE
            App().api.getPercentage(
                key = "5ca609edcemsh37fb5858eaaf930p1c12bejsnfc6d9b8ad6ab",
                host = "love-calculator.p.rapidapi.com",
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            ).enqueue(object : retrofit2.Callback<LoveResult> {
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

    override fun onResume() {
        super.onResume()
        binding.apply {
            progressBar.visibility = View.GONE
            etFirstName.visibility = View.VISIBLE
            etSecondName.visibility = View.VISIBLE
            btnResult.visibility = View.VISIBLE
            etFirstName.text.clear()
            etSecondName.text.clear()
        }
    }
}