package com.example.master_calc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<AppCompatButton>(R.id.simpleCalc).setOnClickListener {
            val intent = Intent(this, Scientific_Calculator::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<AppCompatButton>(R.id.gstCalc).setOnClickListener {
            val intent = Intent(this, Gst_Calculator::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<AppCompatButton>(R.id.currencyConverter).setOnClickListener {
            val intent = Intent(this, Currency_Converter::class.java)
            startActivity(intent)
            finish()
        }
    }
}