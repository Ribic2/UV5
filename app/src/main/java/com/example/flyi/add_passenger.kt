package com.example.flyi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class add_passenger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_passenger)

        val addNewPassengerButton = findViewById<Button>(R.id.addNewPassengerButton)


        addNewPassengerButton.setOnClickListener {
            this.addNewPassenger()

            val switchActivityIntent = Intent(this, passengers::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }

    fun addNewPassenger() {
        var name = findViewById<EditText>(R.id.name)
        var surname = findViewById<EditText>(R.id.surname)
        var age = findViewById<EditText>(R.id.age)
        var street = findViewById<EditText>(R.id.street)
        var houseNumber = findViewById<EditText>(R.id.houseNumber)
        var city = findViewById<EditText>(R.id.city)


        val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        val myEdit = sharedPreferences.edit()
        val gson = Gson()

        val newPassenger = Passenger(
            name.text.toString(),
            surname.text.toString(),
            age.text.toString(),
            houseNumber.text.toString(),
            street.text.toString(),
            city.text.toString()
        )

        if (!sharedPreferences.contains("passengers")) {
            val passengersArray = ArrayList<Passenger>()
            passengersArray.add(newPassenger)

            myEdit.putString("passengers", gson.toJson(passengersArray))
            myEdit.apply()
        } else {
            val passengers = sharedPreferences.getString("passengers", "");
            val type = object : TypeToken<ArrayList<Passenger>>() {}.type
            val arrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)

            arrayList.add(newPassenger)
            myEdit.putString("passengers", gson.toJson(arrayList))
            myEdit.apply()
        }
    }
}