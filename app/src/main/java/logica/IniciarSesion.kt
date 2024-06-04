package logica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.travelbird.databinding.IniciarSesionBinding
import android.util.Patterns

/**
 * Actividad para iniciar sesión en la aplicación.
 */
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

            // Validaciones
            if (password.isNotEmpty() && email.isNotEmpty()) {
                if (emailValido(email)) {
                    // Inicia sesión con Firebase Authentication
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Obtén el usuario actual de Firebase
                            val currentUser = firebaseAuth.currentUser

                            // Verifica si el usuario está autenticado
                            if (currentUser != null) {
                                // Crea un Intent para iniciar la actividad Inicio
                                val intent = Intent(this, Inicio::class.java)
                                // Agrega la información del usuario al Intent
                                intent.putExtra("uid", currentUser.uid)
                                intent.putExtra("displayName", currentUser.displayName)
                                intent.putExtra("email", currentUser.email)
                                // Inicia la actividad Inicio
                                startActivity(intent)
                            } else {
                                // Maneja el caso en que el usuario no está autenticado
                                Toast.makeText(this, "Error de autenticación: No se encontró usuario.", Toast.LENGTH_SHORT).show()
                            }
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
            // Crea un Intent para iniciar la actividad CrearCuenta
            val signupIntent = Intent(this, CrearCuenta::class.java)
            startActivity(signupIntent)
        }
    }

    /**
     * Valida la dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico a validar.
     * @return `true` si el correo electrónico es válido, `false` en caso contrario.
     */
    private fun emailValido(email: CharSequence): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}