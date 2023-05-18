package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class credit_card_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card_info)

        val goToProcessing = findViewById<Button>(R.id.goToProcessing)
        val creditCard = findViewById<EditText>(R.id.cardnumber)
        val experation = findViewById<EditText>(R.id.experation)
            val securitycode = findViewById<EditText>(R.id.securitycode)


        goToProcessing.setOnClickListener {
            val switchActivityIntent = Intent(this, processing_payment::class.java)
            if (!creditCard.text.isEmpty() && !experation.text.isEmpty() && !securitycode.text.isEmpty()) {
                startActivity(switchActivityIntent)
                this.finish()
            }
        }
    }
}