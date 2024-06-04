package logica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.travelbird.databinding.CrearCuentaBinding
import android.util.Patterns
import datos.DatoUsuario

/**
 * Actividad para crear una nueva cuenta de usuario.
 */
class CrearCuenta : AppCompatActivity() {

    private lateinit var binding: CrearCuentaBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance()

        binding.botonCrearCuenta.setOnClickListener {
            val nombre = binding.nameEditText.text.toString().trim()
            val username = binding.userEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.passwordConfirmText.text.toString().trim()

            // Validaciones
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && nombre.isNotEmpty() && username.isNotEmpty()) {
                if (isValidEmail(email)) {
                    if (password == confirmPassword) {
                        // Creación de la cuenta de usuario en Firebase
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { authTask ->
                            if (authTask.isSuccessful) {
                                val userId = firebaseAuth.currentUser?.uid ?: ""
                                // Crear el objeto DatoUsuario para guardar en la base de datos
                                val datoUsuario = DatoUsuario(
                                    id = userId,
                                    nombre = nombre,
                                    correo = email,
                                    nombreUsuario = username
                                    // Considerar no guardar la contraseña por razones de seguridad
                                )
                                // Guardar los datos del usuario en Firebase Realtime Database
                                databaseReference.getReference("usuarios").child(userId).setValue(datoUsuario).addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        Toast.makeText(this, "Cuenta creada exitosamente.", Toast.LENGTH_SHORT).show()
                                        // Redireccionar al usuario a la pantalla de inicio de sesión
                                        val intent = Intent(this, IniciarSesion::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(this, "Error al guardar datos del usuario: ${dbTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(this, "Error al crear cuenta: ${authTask.exception?.message}", Toast.LENGTH_SHORT).show()
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
    }

    /**
     * Valida la dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico a validar.
     * @return `true` si el correo electrónico es válido, `false` en caso contrario.
     */
    private fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}