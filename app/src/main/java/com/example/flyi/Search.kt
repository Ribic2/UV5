package com.example.flyi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.widget.DatePicker


class Search : AppCompatActivity() {
    var cal = Calendar.getInstance()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val from = findViewById<Spinner>(R.id.from)
        val to = findViewById<Spinner>(R.id.to)
        val departure = findViewById<EditText>(R.id.departure)
        val arrival = findViewById<EditText>(R.id.arrival)
        val passengers = findViewById<EditText>(R.id.passengers)
        val one_way = findViewById<Switch>(R.id.one_way)
        val round_trip = findViewById<Switch>(R.id.round_trip)
        val context = this


        // Set dropdowns
        val gson = Gson()
        val jsonString: String = this.assets.open("destinations/destinations.json")
            .bufferedReader().use { it.readText() }
        val connectionsList = object : TypeToken<List<Connection>>() {}.type

        val tos = gson.fromJson<List<Connection>>(jsonString, connectionsList).map { it.to }
        val froms = gson.fromJson<List<Connection>>(jsonString, connectionsList).map { it.from }

        val toAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tos)

        val fromAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, froms)

        from.adapter = fromAdapter
        to.adapter = toAdapter

        from.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        to.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        // Set date dialogs
        arrival.setOnClickListener {
            val c: Calendar = Calendar.getInstance()

            val year: Int = c.get(Calendar.YEAR)
            val month: Int = c.get(Calendar.MONTH)
            val day: Int = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year)
                    arrival.setText(dat)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        departure.setOnClickListener {
            val c: Calendar = Calendar.getInstance()

            val year: Int = c.get(Calendar.YEAR)
            val month: Int = c.get(Calendar.MONTH)
            val day: Int = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year)
                    departure.setText(dat)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        // Set search button

        val searchButton = findViewById<Button>(R.id.search)

        searchButton.setOnClickListener {

            if (passengers.text.isEmpty()) {
                passengers.setText("1")
            }

            val search_params = SearchParams(
                from.selectedItem.toString(),
                to.selectedItem.toString(),
                departure.text.toString(),
                arrival.text.toString(),
                passengers?.text.toString().toInt(),
                one_way.isChecked.toString(),
                round_trip.isChecked.toString(),
            )

            val gson = Gson()
            val connectionsList = object : TypeToken<List<Connection>>() {}.type
            val jsonString: String =
                this.assets.open("destinations/destinations.json").bufferedReader().use { it.readText() }
            val data = gson.fromJson<List<Connection>>(jsonString, connectionsList)
            val find = ArrayList<Connection>()

            // Filter
            val itr = data.listIterator()    // or, use `iterator()`
            while (itr.hasNext()) {
                val conn: Connection = itr.next()
                if ((conn.from == from.selectedItem.toString() && conn.to == to.selectedItem.toString()) || conn.isOneWay == one_way.isChecked) {
                    find.add(conn)
                }
            }


            val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            val myEdit = sharedPreferences.edit()

            if (find.isEmpty()) {
                find.addAll(data)
            }

            myEdit.putString("search_params", gson.toJson(search_params))
            myEdit.putString("destinations", gson.toJson(find))
            myEdit.commit()

            val switchActivityIntent = Intent(this, search_results::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }
}