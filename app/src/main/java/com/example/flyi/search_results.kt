package com.example.flyi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class search_results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

       this.getDestinations()
    }

    private fun getDestinations() {
        val jsonString: String = this.assets.open("destinations/destinations.json")
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        val connectionsList = object : TypeToken<List<Connection>>() {}.type

        val data = gson.fromJson<List<Connection>>(jsonString, connectionsList)

        var mListView = findViewById<ListView>(R.id.results)
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, data
        )

        mListView.adapter = arrayAdapter

    }
}