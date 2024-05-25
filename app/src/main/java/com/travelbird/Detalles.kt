package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Detalles : AppCompatActivity() {

    private lateinit var contenedorFlechaAtras: LinearLayout
    private lateinit var botonFavoritos: Button
    private lateinit var botonItinerario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles)

        contenedorFlechaAtras = findViewById(R.id.container_back_arrow)
        botonFavoritos = findViewById(R.id.boton_favoritos)
        botonItinerario = findViewById(R.id.boton_itinerario)

        setupListeners()
    }

    private fun setupListeners() {
        contenedorFlechaAtras.setOnClickListener {
            // Navegar de regreso a la pantalla de Destino
            val intent = Intent(this, Destino::class.java)
            startActivity(intent)
            finish()
        }

        botonFavoritos.setOnClickListener {
            // Mostrar mensaje de "Añadido a favoritos"
            Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            // Usar un Handler para mantener el mensaje por 3 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "", Toast.LENGTH_SHORT).cancel()
            }, 3000)
        }

        botonItinerario.setOnClickListener {
            // Navegar a la pantalla de Agregar Itinerario
            val intent = Intent(this, AgregarItinerario::class.java)
            startActivity(intent)
        }
    }
}
