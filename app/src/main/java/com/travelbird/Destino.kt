package com.travelbird

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout

class Destino : AppCompatActivity() {

    private lateinit var contenedorFlechaAtras: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destino)

        contenedorFlechaAtras = findViewById(R.id.container_back_arrow)

        setupListeners()
    }

    private fun setupListeners() {
        contenedorFlechaAtras.setOnClickListener {
            // Navegar de regreso a la pantalla de Explorar
            val intent = Intent(this, Explorar::class.java)
            startActivity(intent)
            finish()  // Opcional, termina esta actividad
        }
    }
}
