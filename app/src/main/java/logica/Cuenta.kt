package logica

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.travelbird.R
import com.travelbird.databinding.CuentaBinding

/**
 * Actividad que muestra la información de la cuenta del usuario.
 */
class Cuenta : AppCompatActivity() {

    private lateinit var binding: CuentaBinding
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorExplorar: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contenedorInicio = findViewById(R.id.container_depth_frame158)
        contenedorExplorar = findViewById(R.id.container_depth_frame161)
        contenedorItinerario = findViewById(R.id.container_depth_frame164)

        // Obtén los datos del usuario del Intent
        val nombre = intent.getStringExtra("nombre")
        val nombreUsuario = intent.getStringExtra("nombreUsuario")
        val email = intent.getStringExtra("email")

        // Muestra la información del usuario en la vista
        with(binding) {
            titleName.text = nombre ?: "" // Si no hay nombre, usa una cadena vacía
            titleUsername.text = nombreUsuario ?: ""
            textNombre.text = nombre ?: ""
            textEmail.text = email ?: ""
            textUsuario.text = nombreUsuario ?: ""
        }

        binding.botonFavoritos.setOnClickListener {
            val intent = Intent(this, Favoritos::class.java)
            startActivity(intent)
        }

        binding.botonLogout.setOnClickListener {
            val intent = Intent(this, Bienvenida::class.java)
            startActivity(intent)
        }

        setupListeners()
    }

    /**
     * Configura los listeners para los contenedores de las diferentes secciones de la aplicación.
     */
    private fun setupListeners() {
        contenedorInicio.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }

        contenedorExplorar.setOnClickListener {
            val intent = Intent(this, Explorar::class.java)
            startActivity(intent)
        }

        contenedorItinerario.setOnClickListener {
            val intent = Intent(this, Itinerario::class.java)
            startActivity(intent)
        }
    }
}