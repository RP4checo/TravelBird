package com.travelbird

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AgregarItinerario : AppCompatActivity() {

    private lateinit var contenedorCerrar: LinearLayout
    private lateinit var botonAgregarItinerario: LinearLayout
    private lateinit var editTextFecha: EditText
    private lateinit var editTextHora: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_itinerario)

        contenedorCerrar = findViewById(R.id.container_close)
        botonAgregarItinerario = findViewById(R.id.boton_agregar_itinerario)
        editTextFecha = findViewById(R.id.editTextFecha)
        editTextHora = findViewById(R.id.editTextHora)

        setupListeners()
    }

    private fun setupListeners() {
        contenedorCerrar.setOnClickListener {
            val intent = Intent(this, Detalles::class.java)
            startActivity(intent)
            finish()
        }

        botonAgregarItinerario.setOnClickListener {
            Toast.makeText(this, "Añadido a Itinerario", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "", Toast.LENGTH_SHORT).cancel()
            }, 3000)
        }

        editTextFecha.setOnClickListener {
            mostrarDatePicker()
        }

        editTextHora.setOnClickListener {
            mostrarTimePicker()
        }
    }

    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()
        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                calendario.set(Calendar.YEAR, year)
                calendario.set(Calendar.MONTH, monthOfYear)
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                actualizarEditTextFecha(calendario)
            },
            año,
            mes,
            dia
        )
        datePickerDialog.show()
    }

    private fun mostrarTimePicker() {
        val calendario = Calendar.getInstance()
        val hora = calendario.get(Calendar.HOUR_OF_DAY)
        val minuto = calendario.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendario.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendario.set(Calendar.MINUTE, minute)
                actualizarEditTextHora(calendario)
            },
            hora,
            minuto,
            true
        )
        timePickerDialog.show()
    }

    private fun actualizarEditTextFecha(calendario: Calendar) {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        editTextFecha.setText(formato.format(calendario.time))
    }

    private fun actualizarEditTextHora(calendario: Calendar) {
        val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
        editTextHora.setText(formato.format(calendario.time))
    }
}