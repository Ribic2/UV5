package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.setData()

        val goToAllPassengers = findViewById<Button>(R.id.goToAllPassengers)
        val goBackToAll = findViewById<Button>(R.id.goBackToAll)

        goToAllPassengers.setOnClickListener{
            val switchActivityIntent = Intent(this, passengers::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        goBackToAll.setOnClickListener{
            val switchActivityIntent = Intent(this, search_results::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }


    fun setData(){
        val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val selected_destination = sharedPreferences.getString("selected_destination", "")

        val title = findViewById<TextView>(R.id.title)
        title.text = selected_destination
    }


}