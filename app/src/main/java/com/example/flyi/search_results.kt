package com.example.flyi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class search_results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        this.getDestinations()

        val backToSearch = findViewById<Button>(R.id.backToSearch)

        backToSearch.setOnClickListener {
            val switchActivityIntent = Intent(this, Search::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }


    }

    private fun getDestinations() {
        val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)

        Log.d("messing", sharedPreferences.getString("search_params", "").toString())

        val gson = Gson()
        val jsonString: String? = sharedPreferences.getString("destinations", "")
        val connectionsList = object : TypeToken<List<Connection>>() {}.type

        val data = gson.fromJson<List<Connection>>(jsonString, connectionsList)
        val context = this

        var mListView = findViewById<ListView>(R.id.results)
        val arrayAdapter = ConnectionListAdapter(context, data)

        mListView.adapter = arrayAdapter

        mListView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val itemValue = mListView.getItemAtPosition(p2)

                val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                val myEdit = sharedPreferences.edit()

                myEdit.putString("selected_destination", gson.toJson(itemValue))
                myEdit.apply()

                val switchActivityIntent = Intent(context, detail::class.java)
                startActivity(switchActivityIntent)
                context.finish()
            }

        }

    }
}