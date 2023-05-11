package com.example.flyi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_page_activity)

        android.os.Handler().postDelayed({
            switchActivities()
        }, 3000)
    }

    private fun switchActivities() {
        val switchActivityIntent = Intent(this, Search::class.java)
        startActivity(switchActivityIntent)
        this.finish()
    }
}