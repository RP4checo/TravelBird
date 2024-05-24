package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IniciarSesion : AppCompatActivity() {

    private lateinit var editTextCorreo: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var botonIniciarSesion: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciar_sesion)

        // Inicialización de los componentes de la interfaz
        editTextCorreo = findViewById(R.id.emailEditText)
        editTextContrasena = findViewById(R.id.passwordEditText)
        botonIniciarSesion = findViewById(R.id.container_depth_frame15)

        // Establecer el listener para el botón de iniciar sesión
        botonIniciarSesion.setOnClickListener {
            if (validarCampos()) {
                navegarAInicio()
            } else {
                Toast.makeText(this, "Por favor, ingrese correo y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para validar que los campos de correo y contraseña no estén vacíos
    private fun validarCampos(): Boolean {
        val correo = editTextCorreo.text.toString().trim()
        val contrasena = editTextContrasena.text.toString().trim()
        return correo.isNotEmpty() && contrasena.isNotEmpty()
    }

    // Método para navegar a la pantalla de inicio
    private fun navegarAInicio() {
        val intent = Intent(this, Inicio::class.java)  // Asumiendo que tienes una actividad llamada InicioActivity
        startActivity(intent)
    }
}
