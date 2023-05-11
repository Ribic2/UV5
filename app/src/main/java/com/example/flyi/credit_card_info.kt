package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class credit_card_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card_info)

        val goToProcessing = findViewById<Button>(R.id.goToProcessing)

        goToProcessing.setOnClickListener {
            val switchActivityIntent = Intent(this, processing_payment::class.java)
            startActivity(switchActivityIntent)
            this.finish()
        }
    }
}