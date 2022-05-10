package com.example.realizeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.Jair_Funcion)
        btn.setOnClickListener{
            val lanzar =Intent(this, Jair_Screen_Awards::class.java)
            startActivity(lanzar)
        }

    }
}