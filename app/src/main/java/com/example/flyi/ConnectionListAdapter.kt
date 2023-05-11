package com.example.flyi

import android.app.Activity
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConnectionListAdapter(private val context: Activity, private val destinations: List<Connection>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return destinations.size
    }

    override fun getItem(position: Int): Any {
        return destinations.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        convertView = LayoutInflater.from(context).inflate(R.layout.connection_list, parent, false)

        val destination_from = convertView.findViewById<TextView>(R.id.destination_from)
        val destination_to = convertView.findViewById<TextView>(R.id.destination_to)
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("data", AppCompatActivity.MODE_PRIVATE)

        destination_from.text = destinations.get(position).from
        destination_to.text = destinations.get(position).to
        return convertView
    }
}