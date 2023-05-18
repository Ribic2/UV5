package com.example.flyi

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PassengerListAdapter(private val context: Activity, private val passengersArray: ArrayList<Passenger>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return passengersArray.size
    }

    override fun getItem(position: Int): Any {
        return passengersArray.get(position).name
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
            myEdit.putString("delete_at", position.toString())
            myEdit.commit()


            val switchActivityIntent = Intent(context, delete_passenger::class.java)
            convertView.context.startActivity(switchActivityIntent)
            context.finish()
        }

        nameAndSurname.text = passengersArray.get(position).name + " " + passengersArray.get(position).surname
        age.text = passengersArray.get(position).age
        residency.text =
            passengersArray.get(position).houseNumber + " " + passengersArray.get(position).street + " " + passengersArray.get(position).city
        return convertView
    }
}