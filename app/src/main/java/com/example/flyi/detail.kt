package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.w3c.dom.Text

class detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.setData()

        val goToAllPassengers = findViewById<Button>(R.id.goToAllPassengers)
        val goBackToAll = findViewById<Button>(R.id.goBackToAll)

        goToAllPassengers.setOnClickListener {
            val switchActivityIntent = Intent(this, passengers::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        goBackToAll.setOnClickListener {
            val switchActivityIntent = Intent(this, search_results::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }


    fun setData() {
        val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val selected_destination = sharedPreferences.getString("selected_destination", "")
        val gson = Gson()

        val type = object : TypeToken<Connection>() {}.type
        val destination: Connection = gson.fromJson(selected_destination, type)

        val title = findViewById<TextView>(R.id.title)
        val length = findViewById<TextView>(R.id.detail_length)
        val flightClasses = findViewById<LinearLayout>(R.id.flight_classes)
        val passengers = findViewById<TextView>(R.id.detail_passengers)
        val destinationType = findViewById<TextView>(R.id.detail_type)

        val getAllFlightClasses = destination.classes.map { it.name }


        title.text = destination.from + " " + destination.to
        length.text = destination.connection_length.toString()
        passengers.text = destination.passengers.toString()

        for (classes in getAllFlightClasses) {
            val button = Button(this)
            button.setText(classes)
            button.setOnClickListener {
                val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
                val edit = sharedPreferences.edit()

                edit.putString("class", classes)
                edit.commit()
            }
            flightClasses.addView(button)
        }

        if (destination.isOneWay) {
            destinationType.text = "One-way"
        } else {
            destinationType.text = "Round-type"
        }

    }


}