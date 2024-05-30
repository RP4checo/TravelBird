package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.travelbird.databinding.DestinoBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

        bundle?.let {
            val atraccionesType = object : TypeToken<List<DatoAtraccion>>() {}.type
            val eventosType = object : TypeToken<List<DatoEvento>>() {}.type
            val restaurantesType = object : TypeToken<List<DatoRestaurante>>() {}.type

            val atracciones: List<DatoAtraccion> = Gson().fromJson(it.getString("Atracciones"), atraccionesType)
            val eventos: List<DatoEvento> = Gson().fromJson(it.getString("Eventos"), eventosType)
            val restaurantes: List<DatoRestaurante> = Gson().fromJson(it.getString("Restaurantes"), restaurantesType)

            setupRecyclerViews(atracciones, eventos, restaurantes)
        }

        contenedorFlechaAtras = findViewById(R.id.container_back_arrow)
        setupListeners()
    }

    private fun setupRecyclerViews(atracciones: List<DatoAtraccion>, eventos: List<DatoEvento>, restaurantes: List<DatoRestaurante>) {
        val atraccionesAdapter = AdaptadorAtraccion(this, atracciones)
        binding.recyclerViewAtracciones.adapter = atraccionesAdapter
        binding.recyclerViewAtracciones.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val eventosAdapter = AdaptadorEvento(this, eventos)
        binding.recyclerViewEventos.adapter = eventosAdapter
        binding.recyclerViewEventos.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val restaurantesAdapter = AdaptadorRestaurante(this, restaurantes)
        binding.recyclerViewRestaurantes.adapter = restaurantesAdapter
        binding.recyclerViewRestaurantes.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
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