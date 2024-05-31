package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.travelbird.databinding.IniciarSesionBinding
import android.util.Patterns

class IniciarSesion : AppCompatActivity() {

    private lateinit var binding: IniciarSesionBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.botonIniciarSesion.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (password.isNotEmpty() && email.isNotEmpty()) {
                if (emailValido(email)) {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, Inicio::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Error de autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Por favor, ingrese una contraseña.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.containerCrearCuenta.setOnClickListener {
            val signupIntent = Intent(this, CrearCuenta::class.java)
            startActivity(signupIntent)
        }
    }

    private fun emailValido(email: CharSequence): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
