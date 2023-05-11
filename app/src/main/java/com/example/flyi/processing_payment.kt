package com.example.flyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class processing_payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing_payment)

        android.os.Handler().postDelayed({
            switchActivities()
        }, 3000)
    }

    private fun switchActivities() {
        val switchActivityIntent = Intent(this, payment_successful::class.java)
        startActivity(switchActivityIntent)
        this.finish()
    }
}