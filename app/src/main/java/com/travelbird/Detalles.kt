package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.travelbird.databinding.DetallesBinding

class Detalles : AppCompatActivity() {

    var imageUrl = ""
    private lateinit var cerrarPantalla: ImageView
    private lateinit var botonFavoritos: Button
    private lateinit var botonItinerario: Button
    private lateinit var binding: DetallesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras

        if (bundle != null) {
            binding.nombreDetalle.text = bundle.getString("nombre")
            binding.descripcionDetalle.text = bundle.getString("descripcion")
            imageUrl = bundle.getString("imagen")!!
            Glide.with(this).load(bundle.getString("imagen")).into(binding.imgDetalle)
            binding.precioDetalle.text = bundle.getDouble("precioEstimado").toString()
            binding.ubicacionDetalle.text = bundle.getString("ubicacion")
            // Convertir el arreglo de categorías de nuevo a lista
            val categoriasArray = bundle.getStringArray("categorias")
            val categoriasList = categoriasArray?.toList() ?: listOf() // Convertir a lista o usar una lista vacía si es nulo

            // Unir las categorías en una cadena con comas
            val categoriasText = categoriasList.joinToString(", ")

            // Mostrar las categorías en el TextView
            binding.categoriaDetalle.text = categoriasText
        }

        cerrarPantalla = findViewById(R.id.container_back_arrow)
        botonFavoritos = findViewById(R.id.boton_favoritos)
        botonItinerario = findViewById(R.id.boton_itinerario)

        setupListeners()
    }

    private fun setupListeners() {
        cerrarPantalla.setOnClickListener {
            // Navegar de regreso a la pantalla de Destino
            val intent = Intent(this, Explorar::class.java)
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
            //val intent = Intent(this, AgregarItinerario::class.java)
            //startActivity(intent)
            Toast.makeText(this, "Añadido a Itinerario", Toast.LENGTH_SHORT).show()
            // Usar un Handler para mantener el mensaje por 3 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "", Toast.LENGTH_SHORT).cancel()
            }, 3000)
        }
    }
}