package com.travelbird

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {

    private lateinit var iconoLupa: ImageView
    private lateinit var contenedorExplorar: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout
    private lateinit var contenedorCuenta: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        // Inicialización de los componentes de la UI
        iconoLupa = findViewById(R.id.icon_lupa)
        contenedorExplorar = findViewById(R.id.container_explorer)
        contenedorItinerario = findViewById(R.id.container_itinerario)
        contenedorCuenta = findViewById(R.id.container_cuenta)

        // Configuración de los listeners para cada componente
        iconoLupa.setOnClickListener {
            abrirExplorar()
        }

        contenedorExplorar.setOnClickListener {
            abrirExplorar()
        }

        contenedorItinerario.setOnClickListener {
            abrirItinerario()
        }

        contenedorCuenta.setOnClickListener {
            abrirCuenta()
        }
    }

    private fun abrirExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
    }

    private fun abrirItinerario() {
        val intent = Intent(this, Itinerario::class.java)
        startActivity(intent)
    }

    private fun abrirCuenta() {
        val intent = Intent(this, Cuenta::class.java)
        startActivity(intent)
    }
}
