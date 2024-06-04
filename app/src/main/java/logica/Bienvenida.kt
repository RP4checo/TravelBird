package logica

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.travelbird.R

/**
 * Actividad de bienvenida de la aplicación.
 */
class Bienvenida : AppCompatActivity() {

    private lateinit var botonIniciar: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bienvenida)

        // Inicialización del LinearLayout que funciona como botón
        botonIniciar = findViewById(R.id.container_depth_frame4)

        // Establecer el listener para el botón de iniciar sesión
        botonIniciar.setOnClickListener {
            abrirIniciarSesion()
        }
    }

    /**
     * Método para abrir la pantalla de iniciar sesión.
     */
    private fun abrirIniciarSesion() {
        val intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }
}