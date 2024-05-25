package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Itinerario : AppCompatActivity() {

    private lateinit var contenedorCerrar: LinearLayout
    private lateinit var botonListo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itinerario)

        contenedorCerrar = findViewById(R.id.container_close)
        botonListo = findViewById(R.id.boton_listo)

        setupListeners()
    }

    private fun setupListeners() {
        // Listener para el contenedor que cierra la pantalla y regresa a Explorar
        contenedorCerrar.setOnClickListener {
            navegarAExplorar()
        }

        // Listener para el botón que indica que el usuario ha terminado y regresa a Explorar
        botonListo.setOnClickListener {
            navegarAExplorar()
        }
    }

    private fun navegarAExplorar() {
        // Función para navegar de regreso a la pantalla de Explorar
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
        finish()
    }
}
