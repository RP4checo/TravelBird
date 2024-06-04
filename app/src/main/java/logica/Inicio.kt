package logica

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.travelbird.R

/**
 * Actividad principal de la aplicación, que muestra la pantalla de inicio.
 */
class Inicio : AppCompatActivity() {

    private lateinit var iconoLupa: ImageView
    private lateinit var contenedorExplorar: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout
    private lateinit var contenedorCuenta: LinearLayout
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        // Inicializa Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Inicialización de los componentes de la UI
        iconoLupa = findViewById(R.id.icon_lupa)
        contenedorExplorar = findViewById(R.id.container_explorer)
        contenedorItinerario = findViewById(R.id.container_itinerario)
        contenedorCuenta = findViewById(R.id.container_cuenta)

        // Configuración de los listeners para cada componente
        iconoLupa.setOnClickListener {
            abrirExplorar()
        }

        contenedorExplorar.setOnClickListener {
            abrirExplorar()
        }

        contenedorItinerario.setOnClickListener {
            abrirItinerario()
        }

        contenedorCuenta.setOnClickListener {
            abrirCuenta()
        }
    }

    /**
     * Abre la actividad de Explorar.
     */
    private fun abrirExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
    }

    /**
     * Abre la actividad de Itinerario.
     */
    private fun abrirItinerario() {
        val intent = Intent(this, Itinerario::class.java)
        startActivity(intent)
    }

    /**
     * Abre la actividad de Cuenta.
     */
    private fun abrirCuenta() {
        val currentUser = firebaseAuth.currentUser
        val intent = Intent(this, Cuenta::class.java)
        // Agrega la información del usuario al Intent
        if (currentUser != null) {
            intent.putExtra("uid", currentUser.uid)
            intent.putExtra("nombre", currentUser.displayName)
            intent.putExtra("correo", currentUser.email)
        }
        startActivity(intent)
    }
}