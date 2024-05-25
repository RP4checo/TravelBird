package com.travelbird

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Explorar : AppCompatActivity() {

    private lateinit var textoBuscarDestino: TextView
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout
    private lateinit var contenedorCuenta: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explorar)

        textoBuscarDestino = findViewById(R.id.text_buscar_destino)
        contenedorInicio = findViewById(R.id.container_inicio)
        contenedorItinerario = findViewById(R.id.container_itinerario)
        contenedorCuenta = findViewById(R.id.container_cuenta)

        setupListeners()
    }

    private fun setupListeners() {
        textoBuscarDestino.setOnClickListener {
            // Hacer el EditText manipulable para la búsqueda
            textoBuscarDestino.isEnabled = true
            textoBuscarDestino.requestFocus()
        }

        contenedorInicio.setOnClickListener {
            // Navegar a la pantalla de inicio
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }

        contenedorItinerario.setOnClickListener {
            // Navegar a la pantalla de itinerario
            val intent = Intent(this, Itinerario::class.java)
            startActivity(intent)
        }

        contenedorCuenta.setOnClickListener {
            // Navegar a la pantalla de cuenta
            val intent = Intent(this, Cuenta::class.java)
            startActivity(intent)
        }
    }
}
