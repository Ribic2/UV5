package com.example.flyi

import android.app.Activity
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PassengerListAdapter(private val context: Activity, private val passengers: ArrayList<Passenger>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return passengers.size
    }

    override fun getItem(position: Int): Any {
        return passengers.get(position).name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        convertView = LayoutInflater.from(context).inflate(R.layout.passenger_list, parent, false)

        val nameAndSurname = convertView.findViewById<TextView>(R.id.passenger_name_and_surname)
        val age = convertView.findViewById<TextView>(R.id.passenger_age)
        val residency = convertView.findViewById<TextView>(R.id.passenger_residency)

        val sharedPreferences: SharedPreferences = context.getSharedPreferences("data", AppCompatActivity.MODE_PRIVATE)
        val deleteButton = convertView.findViewById<Button>(R.id.delete_passenger_button)
        val myEdit = sharedPreferences.edit()

        deleteButton.setOnClickListener {
            val gson = Gson()
            val passengers = sharedPreferences.getString("passengers", "");
            val type = object : TypeToken<ArrayList<Passenger>>() {}.type
            val arrayList: ArrayList<Passenger> = gson.fromJson(passengers, type)

            arrayList.removeAt(position)
            myEdit.putString("passengers", gson.toJson(arrayList))
            myEdit.apply()
            notifyDataSetChanged();
        }

        nameAndSurname.text = passengers.get(position).name + " " + passengers.get(position).surname
        age.text = passengers.get(position).age
        residency.text =
            passengers.get(position).houseNumber + " " + passengers.get(position).street + " " + passengers.get(position).city
        return convertView
    }
}