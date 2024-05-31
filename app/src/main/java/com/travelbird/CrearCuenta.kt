package com.travelbird

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import com.travelbird.databinding.CrearCuentaBinding

class CrearCuenta : AppCompatActivity() {

    private lateinit var binding: CrearCuentaBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.botonCrearCuenta.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.passwordConfirmText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (isValidEmail(email)) {
                    if (password == confirmPassword) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Cuenta creada exitosamente.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, IniciarSesion::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Error al crear cuenta: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "La contraseña no coincide.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Los campos no pueden estar vacíos.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.containerClose.setOnClickListener {
            finish()
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
