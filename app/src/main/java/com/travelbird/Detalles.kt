package com.travelbird

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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

    @SuppressLint("ShowToast")
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
            // Mostrar la ventana emergente para agregar al itinerario
            showItinerarioPopup()
        }
    }

    private fun showItinerarioPopup() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.itinerario_popup, null)

        // Obtener el TextView después de inflar el layout
        val nombreLugarTextView = view.findViewById<TextView>(R.id.popup_nombre)
        nombreLugarTextView.text = binding.nombreDetalle.text.toString()

        val datePicker = view.findViewById<DatePicker>(R.id.popup_datepicker)
        val timePicker = view.findViewById<TimePicker>(R.id.popup_timepicker)
        //val button = view.findViewById<Button>(R.id.popup_button)

        builder.setView(view)

        builder.setPositiveButton("Agregar", DialogInterface.OnClickListener { _, _ ->
            val selectedDate = datePicker.dayOfMonth.toString().padStart(2, '0') + "/" +
                    (datePicker.month + 1).toString().padStart(2, '0') + "/" + datePicker.year
            val selectedTime = timePicker.hour.toString().padStart(2, '0') + ":" +
                    timePicker.minute.toString().padStart(2, '0')

            // Obtener el ID del lugar (atracción, evento o restaurante)
            val id = intent.getStringExtra("id")

            // Obtener el ID del usuario actual
            val userId = FirebaseAuth.getInstance().currentUser?.uid

            // Validar si el usuario está autenticado
            if (userId != null) {
                // Crear un objeto DatoItinerario
                val datoItinerario = DatoItinerario(id!!, binding.nombreDetalle.text.toString(), userId, selectedDate, selectedTime)

                // Agregar el itinerario a Firebase
                addItinerarioToFirebase(datoItinerario)

                // Mostrar mensaje de éxito
                Toast.makeText(this, "${nombreLugarTextView.text} agregado al itinerario", Toast.LENGTH_SHORT).show()
            } else {
                // Mostrar mensaje de error si no está autenticado
                Toast.makeText(this, "Debes iniciar sesión para agregar al itinerario", Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, _ ->
            dialog.dismiss()
        })

        builder.show()
    }

    private fun addItinerarioToFirebase(datoItinerario: DatoItinerario) {
        val database = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val itinerarioRef = database.getReference("itinerarios/$userId")

        val itinerarioKey = itinerarioRef.push().key // Genera una clave única para el itinerario
        datoItinerario.imagen = imageUrl // Asignar la URL de la imagen
        itinerarioRef.child(itinerarioKey!!).setValue(datoItinerario)
    }
}