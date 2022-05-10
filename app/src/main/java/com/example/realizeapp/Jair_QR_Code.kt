package com.example.realizeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realizeapp.databinding.ActivityJairQrCodeBinding
import com.google.zxing.integration.android.IntentIntegrator


class Jair_QR_Code : AppCompatActivity() {
    private  lateinit var binding:ActivityJairQrCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJairQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScan.setOnClickListener { initScan() }
    }

    private fun initScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null)
        {
            if(result.contents == null)
            {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            else if(result.contents == "hola")
            {
                val intent: Intent = Intent(this, Jair_Counter:: class.java )
                startActivity(intent)
                Toast.makeText(this, "El valor escaneado es: ${result.contents}", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}