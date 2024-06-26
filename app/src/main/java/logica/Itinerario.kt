package logica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.travelbird.R
import com.travelbird.databinding.ItinerarioBinding
import datos.AdaptadorItinerario
import datos.DatoItinerario

/**
 * Actividad que muestra el itinerario del usuario.
 */
class Itinerario : AppCompatActivity() {

    private lateinit var contenedorCerrar: ImageView
    private lateinit var botonListo: Button
    private lateinit var binding: ItinerarioBinding
    private lateinit var database: DatabaseReference
    private lateinit var itinerarioAdapter: AdaptadorItinerario
    private lateinit var itinerarioList: MutableList<DatoItinerario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItinerarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contenedorCerrar = findViewById(R.id.container_close)
        botonListo = findViewById(R.id.boton_listo)

        // Inicializar Firebase Database
        database = FirebaseDatabase.getInstance().getReference("itinerarios")

        // Inicializar la lista de items del itinerario
        itinerarioList = mutableListOf()

        // Inicializar el adaptador del RecyclerView
        itinerarioAdapter = AdaptadorItinerario(itinerarioList, this,
            onEditClickListener = { destino ->
                showEditarItinerarioPopup(destino)
            },
            onDeleteClickListener = { destino ->
                eliminarDestinoDelItinerario(destino)
            }
        )

        // Configurar el RecyclerView
        binding.recyclerViewDestino.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDestino.adapter = itinerarioAdapter

        // Obtener el ID del usuario actual
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        // Escuchar los cambios en la colección de itinerarios del usuario
        if (userId != null) {
            database.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Limpiar la lista de itinerarios actual y cargar los datos desde la base de datos
                    itinerarioList.clear()
                    for (itemSnapshot in snapshot.children) {
                        val itinerarioItem = itemSnapshot.getValue(DatoItinerario::class.java)
                        if (itinerarioItem != null) {
                            // Asignar el ID del itinerario desde la clave del snapshot
                            itinerarioItem.id = itemSnapshot.key
                            itinerarioList.add(itinerarioItem)
                        }
                    }
                    itinerarioAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Itinerario, "Error al obtener el itinerario", Toast.LENGTH_SHORT).show()
                }
            })
        }

        setupListeners()
    }

    /**
     * Configura los listeners para los botones "Cerrar" y "Listo".
     */
    private fun setupListeners() {
        contenedorCerrar.setOnClickListener {
            navegarAExplorar()
        }

        botonListo.setOnClickListener {
            navegarAExplorar()
        }
    }

    /**
     * Navega a la pantalla de "Explorar".
     */
    private fun navegarAExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Muestra un AlertDialog para editar un destino del itinerario.
     *
     * @param destino El objeto DatoItinerario que se va a editar.
     */
    private fun showEditarItinerarioPopup(destino: DatoItinerario) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.itinerario_popup, null)

        val nombreLugarTextView = view.findViewById<TextView>(R.id.popup_nombre)
        nombreLugarTextView.text = destino.nombre

        val datePicker = view.findViewById<DatePicker>(R.id.popup_datepicker)
        val timePicker = view.findViewById<TimePicker>(R.id.popup_timepicker)

        // Preestablecer la fecha y hora del destino
        val dateParts = destino.fecha?.split("/")
        val timeParts = destino.hora?.split(":")
        if (dateParts != null && timeParts != null) {
            datePicker.init(dateParts[2].toInt(), dateParts[1].toInt() - 1, dateParts[0].toInt(), null)
            timePicker.hour = timeParts[0].toInt()
            timePicker.minute = timeParts[1].toInt()
        }

        builder.setView(view)
        builder.setPositiveButton("Actualizar") { _, _ ->
            val selectedDate = datePicker.dayOfMonth.toString().padStart(2, '0') + "/" +
                    (datePicker.month + 1).toString().padStart(2, '0') + "/" + datePicker.year
            val selectedTime = timePicker.hour.toString().padStart(2, '0') + ":" +
                    timePicker.minute.toString().padStart(2, '0')

            // Actualizar la fecha y hora del destino
            destino.fecha = selectedDate
            destino.hora = selectedTime

            // Actualizar el adaptador con los cambios
            val position = itinerarioList.indexOfFirst { it.id == destino.id }
            if (position != -1) {
                itinerarioList[position] = destino
                itinerarioAdapter.notifyItemChanged(position)
            }

            // Actualizar el destino en Firebase
            actualizarDestinoEnFirebase(destino)
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    /**
     * Actualiza un destino en Firebase Realtime Database.
     *
     * @param destino El objeto DatoItinerario que se va a actualizar.
     */
    private fun actualizarDestinoEnFirebase(destino: DatoItinerario) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            destino.id?.let { destinoId ->
                val itinerarioRef = database.child(uid).child(destinoId)
                itinerarioRef.setValue(destino).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Destino actualizado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al actualizar destino", Toast.LENGTH_SHORT).show()
                    }
                }
            } ?: Toast.makeText(this, "Error: ID de destino no encontrado", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Elimina un destino del itinerario en Firebase Realtime Database.
     *
     * @param destino El objeto DatoItinerario que se va a eliminar.
     */
    private fun eliminarDestinoDelItinerario(destino: DatoItinerario) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val itinerarioRef = destino.id?.let { database.child(userId).child(it) }
            if (itinerarioRef != null) {
                itinerarioRef.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Destino eliminado", Toast.LENGTH_SHORT).show()
                        itinerarioList.remove(destino)
                        itinerarioAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this, "Error al eliminar destino", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}