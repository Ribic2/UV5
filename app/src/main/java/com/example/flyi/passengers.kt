package com.example.flyi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class passengers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passengers)

        val addPassengers = findViewById<Button>(R.id.addNewPassenger)
        val goBackToDetail = findViewById<Button>(R.id.goBackToDetail)
        val goToPayment = findViewById<Button>(R.id.goToPayment)
        val price = findViewById<TextView>(R.id.price)

        addPassengers.setOnClickListener {
            val switchActivityIntent = Intent(this, add_passenger::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        goToPayment.setOnClickListener {
            val switchActivityIntent = Intent(this, credit_card_info::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        if (this.getNumberOfPassengers().passengers == this.getNumberOfAddedPassengers()) {
            addPassengers.visibility = View.INVISIBLE
        }

        goBackToDetail.setOnClickListener {
            val switchActivityIntent = Intent(this, detail::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }

        price.text = calculatePrice().toString()
        this.addPassengersToText()
    }

    private fun getNumberOfAddedPassengers(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        val gson = Gson()
        val passengers = sharedPreferences.getString("passengers", "");
        val type = object : TypeToken<ArrayList<Passenger>>() {}.type
        val arrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)

        return arrayList.size
    }

    private fun getNumberOfPassengers(): SearchParams {

        val gson = Gson()
        val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        val searchParams = sharedPreferences.getString("search_params", "")
        val type = object : TypeToken<SearchParams>() {}.type
        val data: SearchParams = gson.fromJson(searchParams, type)

        return data;
    }

    private fun addPassengersToText() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        val listview = findViewById<ListView>(R.id.results_passengers)

        val gson = Gson()
        val passengers = sharedPreferences.getString("passengers", "");
        val type = object : TypeToken<ArrayList<Passenger>>() {}.type
        val arrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)


        val passengerAdapter = PassengerListAdapter(this, arrayList)
        listview.adapter = passengerAdapter
    }

    private fun calculatePrice(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        val gson = Gson()
        val passengers = sharedPreferences.getString("passengers", "")
        val selected_destination = sharedPreferences.getString("selected_destination", "")
        var calculation: Int = 0

        val type = object : TypeToken<ArrayList<Passenger>>() {}.type

        val passengersArrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)
        val connection: Connection = gson.fromJson(selected_destination, Connection::class.java)

        val flightClass = connection.classes[0]


        if(passengersArrayList.size == 0){
            return 0
        }
        passengersArrayList.forEach {
            if (it.age.toString().toInt() < 12) {
                calculation += flightClass.priceKid
            }

            if (it.age.toString().toInt() in 12..17) {
                calculation += flightClass.priceTeen
            }

            if (it.age.toString().toInt() >= 18) {
                calculation += flightClass.priceAdult
            }
        }

        return calculation

    }
}