package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearCuenta : AppCompatActivity() {

    private lateinit var editTextCorreo: EditText
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var botonCrearCuenta: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_cuenta)

        // Referencias a los EditText y al LinearLayout que actúa como botón
        editTextCorreo = findViewById(R.id.emailEditText)
        editTextUsuario = findViewById(R.id.usuarioEditText_cuenta)
        editTextContrasena = findViewById(R.id.passwordEditText)
        botonCrearCuenta = findViewById(R.id.boton_crear_cuenta)  // Asegúrate de agregar el ID correcto en tu XML.

        // Establecer el listener para el botón de crear cuenta
        botonCrearCuenta.setOnClickListener {
            if (validarCampos()) {
                abrirIniciarSesion()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validar que los campos no estén vacíos
    private fun validarCampos(): Boolean {
        val correo = editTextCorreo.text.toString().trim()
        val usuario = editTextUsuario.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()
        return correo.isNotEmpty() && usuario.isNotEmpty() && contrasena.isNotEmpty()
    }

    // Método para abrir la pantalla de iniciar sesión
    private fun abrirIniciarSesion() {
        val intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }
}
