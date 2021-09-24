package com.hobermac.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity()
{
    private var userF : EditText? = null
    private var mainBtn : Button? = null
    private var result : TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userF = findViewById(R.id.user_field)
        mainBtn = findViewById(R.id.main_btn)
        result = findViewById(R.id.result_info)

        mainBtn?.setOnClickListener()
        {
            if(userF?.text?.toString()?.equals("")!!)
            {
                Toast.makeText(this, "Enter city", Toast.LENGTH_LONG).show()
            }
            else
            {
                val city: String = userF?.text.toString()
                val key: String = "e43e4202b2a5a7db6b458d40ff9d6eac"
                val url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key"

                doAsync {
                    val apiResponse = URL(url).readText()
                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")
                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")

                    result?.text = "Temperature: $temp\n$desc"
                }
            }
        }
    }
}