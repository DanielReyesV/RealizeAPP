package com.example.realizeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Jair_Screen_Awards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jair_screen_awards)

        val btn = findViewById<Button>(R.id.btnPremio)
        btn.setOnClickListener{
            val lanzar = Intent(this, Jair_QR_Code::class.java)
            startActivity(lanzar)
        }

    }
}