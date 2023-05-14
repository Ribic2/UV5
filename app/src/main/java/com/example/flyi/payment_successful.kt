package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class payment_successful : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_successful)

        val goBackHome = findViewById<Button>(R.id.goBackHome)

        goBackHome.setOnClickListener {
            val switchActivityIntent = Intent(this, search_results::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }
}