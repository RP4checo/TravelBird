package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Favoritos : AppCompatActivity() {

    private lateinit var contenedorCuenta1: LinearLayout
    private lateinit var contenedorCuenta2: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favoritos)

        contenedorCuenta1 = findViewById(R.id.container_depth_frame172)
        contenedorCuenta2 = findViewById(R.id.container_depth_frame211)

        setupListeners()
    }

    private fun setupListeners() {
        contenedorCuenta1.setOnClickListener {
            navegarACuenta()
        }

        contenedorCuenta2.setOnClickListener {
            navegarACuenta()
        }
    }

    private fun navegarACuenta() {
        val intent = Intent(this, Cuenta::class.java)
        startActivity(intent)
    }
}
