package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Cuenta : AppCompatActivity() {

    private lateinit var contenedorFavoritos: LinearLayout
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorExplorar: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cuenta)

        contenedorFavoritos = findViewById(R.id.container_depth_frame128)
        contenedorInicio = findViewById(R.id.container_depth_frame158)
        contenedorExplorar = findViewById(R.id.container_depth_frame161)
        contenedorItinerario = findViewById(R.id.container_depth_frame164)

        setupListeners()
    }

    private fun setupListeners() {
        contenedorFavoritos.setOnClickListener {
            navegarAFavoritos()
        }

        contenedorInicio.setOnClickListener {
            navegarAInicio()
        }

        contenedorExplorar.setOnClickListener {
            navegarAExplorar()
        }

        contenedorItinerario.setOnClickListener {
            navegarAItinerario()
        }
    }

    private fun navegarAFavoritos() {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }

    private fun navegarAInicio() {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    private fun navegarAExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
    }

    private fun navegarAItinerario() {
        val intent = Intent(this, Itinerario::class.java)
        startActivity(intent)
    }
}
