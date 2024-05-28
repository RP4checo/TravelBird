package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.travelbird.databinding.DestinoBinding

class Destino : AppCompatActivity() {

    var imageUrl = ""
    private lateinit var binding: DestinoBinding
    private lateinit var contenedorFlechaAtras: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DestinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras

        if (bundle != null) {
            binding.paisDestino.text = bundle.getString("Pais")
            imageUrl = bundle.getString("Imagen")!!
            Glide.with(this).load(bundle.getString("Imagen")).into(binding.imagenDestino)
            binding.nombreDestino.text = bundle.getString("Nombre")
            binding.descripcionDestino.text = bundle.getString("Descripción")
        }

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
