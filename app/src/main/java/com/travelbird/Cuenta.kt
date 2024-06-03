package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import android.app.AlertDialog
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.travelbird.databinding.CuentaBinding

class Cuenta : AppCompatActivity() {

    private lateinit var binding: CuentaBinding
    private lateinit var sessionManager: GestorSesion
    private lateinit var contenedorFavoritos: LinearLayout
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorExplorar: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = GestorSesion(this) // Pasa 'this' (contexto de la actividad)
        val datoUsuario = sessionManager.getUser()

        contenedorFavoritos = findViewById(R.id.container_depth_frame128)
        contenedorInicio = findViewById(R.id.container_depth_frame158)
        contenedorExplorar = findViewById(R.id.container_depth_frame161)
        contenedorItinerario = findViewById(R.id.container_depth_frame164)

        setupListeners()

        if (datoUsuario != null) {
            binding.textNombre.text = datoUsuario.nombre
            binding.containerNombre.findViewById<TextView>(R.id.container_depth_frame132).text = datoUsuario.nombre
            binding.containerCumple.findViewById<TextView>(R.id.container_depth_frame140).text = datoUsuario.fechaNacimiento
            binding.containerCelular.findViewById<TextView>(R.id.container_depth_frame144).text = datoUsuario.telefono
            binding.containerEmail.findViewById<TextView>(R.id.container_depth_frame148_1).text = datoUsuario.correo // Establecer el correo electrónico
            if (!datoUsuario.fotoPerfil.isNullOrEmpty()) {
                Picasso.get().load(datoUsuario.fotoPerfil).into(binding.containerImgPerfil)
            }
        } else {
            // Maneja el caso donde no hay usuario en la sesión
        }

        // Configura listeners para los contenedores
        binding.containerImgPerfil.setOnClickListener {
            showImagePickerDialog()
        }

        binding.containerDepthFrame124.setOnClickListener {
            showEditNameDialog()
        }

        binding.containerNombre.setOnClickListener {
            showEditNameDialog()
        }

        binding.containerCumple.setOnClickListener {
            showEditBirthdayDialog()
        }

        binding.containerCelular.setOnClickListener {
            showEditPhoneDialog()
        }

        binding.containerEmail.setOnClickListener {
            showEditEmailDialog()
        }
    }

    private fun setupListeners() {
        contenedorFavoritos.setOnClickListener {
            navegarAFavoritos()
        }

        contenedorInicio.setOnClickListener {
            navegarAInicio()
        }

        contenedorExplorar.setOnClickListener {
            navegarAExplorar()
        }

        contenedorItinerario.setOnClickListener {
            navegarAItinerario()
        }
    }

    private fun navegarAFavoritos() {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }

    private fun navegarAInicio() {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }

    private fun navegarAExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
    }

    private fun navegarAItinerario() {
        val intent = Intent(this, Itinerario::class.java)
        startActivity(intent)
    }

    // Ventana emergente para cambiar la imagen de perfil
    private fun showImagePickerDialog() {
        // ... lógica para abrir el selector de imágenes
    }

    // Ventana emergente para editar el nombre
    private fun showEditNameDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_text_dialog, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextDialog)
        editText.setText(binding.textNombre.text.toString())

        builder.setView(dialogView)
            .setPositiveButton("Aceptar") { dialog, which ->
                val newName = editText.text.toString()
                binding.textNombre.text = newName
                binding.containerNombre.findViewById<TextView>(R.id.container_depth_frame132).text = newName
                // Guarda el nuevo nombre en la base de datos o la sesión
                // ...
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }

    // Ventana emergente para editar la fecha de nacimiento
    private fun showEditBirthdayDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_text_dialog, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextDialog)
        editText.setText(binding.containerCumple.findViewById<TextView>(R.id.container_depth_frame140).text.toString())

        builder.setView(dialogView)
            .setPositiveButton("Aceptar") { dialog, which ->
                val newBirthday = editText.text.toString()
                binding.containerCumple.findViewById<TextView>(R.id.container_depth_frame140).text = newBirthday
                // Guarda la nueva fecha de nacimiento en la base de datos o la sesión
                // ...
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }

    // Ventana emergente para editar el número de teléfono
    private fun showEditPhoneDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_text_dialog, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextDialog)
        editText.setText(binding.containerCelular.findViewById<TextView>(R.id.container_depth_frame144).text.toString())

        builder.setView(dialogView)
            .setPositiveButton("Aceptar") { dialog, which ->
                val newPhone = editText.text.toString()
                binding.containerCelular.findViewById<TextView>(R.id.container_depth_frame144).text = newPhone
                // Guarda el nuevo número de teléfono en la base de datos o la sesión
                // ...
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }

    // Ventana emergente para editar el correo electrónico
    private fun showEditEmailDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_text_dialog, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextDialog)
        editText.setText(binding.containerEmail.findViewById<TextView>(R.id.container_depth_frame148_1).text.toString())

        builder.setView(dialogView)
            .setPositiveButton("Aceptar") { dialog, which ->
                val newEmail = editText.text.toString()
                binding.containerEmail.findViewById<TextView>(R.id.container_depth_frame148_1).text = newEmail
                // Guarda el nuevo correo electrónico en la base de datos o la sesión
                // ...
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }
        builder.show()
    }
}