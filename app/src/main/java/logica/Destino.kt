package logica

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
import com.travelbird.R
import datos.AdaptadorAtraccion
import datos.AdaptadorEvento
import datos.AdaptadorRestaurante
import datos.DatoAtraccion
import datos.DatoEvento
import datos.DatoRestaurante

/**
 * Actividad que muestra los detalles de un destino turístico.
 */
class Destino : AppCompatActivity() {

    private lateinit var binding: DestinoBinding
    private lateinit var contenedorFlechaAtras: ImageView
    private var imageUrl = "" // Se declara fuera del onCreate para que pueda ser usada en setupListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DestinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos del destino del Intent
        val bundle = intent.extras
        if (bundle != null) {
            binding.paisDestino.text = bundle.getString("Pais")
            imageUrl = bundle.getString("Imagen")!! // Actualizar imageUrl con la imagen del destino
            Glide.with(this).load(bundle.getString("Imagen")).into(binding.imagenDestino)
            binding.nombreDestino.text = bundle.getString("Nombre")
            binding.descripcionDestino.text = bundle.getString("Descripción")
        }

        // Obtener las listas de atracciones, eventos y restaurantes desde el Intent
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

    /**
     * Configura los RecyclerView para mostrar atracciones, eventos y restaurantes.
     *
     * @param atracciones Lista de atracciones.
     * @param eventos Lista de eventos.
     * @param restaurantes Lista de restaurantes.
     */
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

    /**
     * Configura el listener para el botón de flecha atrás.
     */
    private fun setupListeners() {
        contenedorFlechaAtras.setOnClickListener {
            // Navegar de regreso a la pantalla de Explorar
            val intent = Intent(this, Explorar::class.java)
            startActivity(intent)
            finish()  // Opcional, termina esta actividad
        }
    }
}