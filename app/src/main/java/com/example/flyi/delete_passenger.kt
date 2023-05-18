package com.example.flyi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class delete_passenger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_passenger)

        val deletePassengerButton = findViewById<Button>(R.id.deleteUserButton)
        val goBackToAllPassengers = findViewById<Button>(R.id.goBackToAllPassengerFromDelete)


        deletePassengerButton.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences("data", AppCompatActivity.MODE_PRIVATE)
            val deleteAt = sharedPreferences.getString("delete_at", "0").toString().toInt()
            val myEdit = sharedPreferences.edit()

            val gson = Gson()
            val passengers = sharedPreferences.getString("passengers", "");
            val type = object : TypeToken<ArrayList<Passenger>>() {}.type
            val arrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)

            arrayList.removeAt(deleteAt)
            myEdit.putString("passengers", gson.toJson(arrayList))
            myEdit.apply()

            val switchActivityIntent = Intent(this, com.example.flyi.passengers::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        // Go back to all passengers
        goBackToAllPassengers.setOnClickListener {
            val switchActivityIntent = Intent(this, passengers::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }
}