package logica

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.travelbird.R
import com.travelbird.databinding.ExplorarBinding
import datos.AdaptadorDestino
import datos.DatoDestino
import java.util.Locale

/**
 * Actividad que muestra una lista de destinos turísticos.
 */
class Explorar : AppCompatActivity() {

    private lateinit var binding: ExplorarBinding
    private lateinit var dataList: ArrayList<DatoDestino>
    private lateinit var adapter: AdaptadorDestino
    private var databaseReference: DatabaseReference? = null
    private var eventListener: ValueEventListener? = null
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout
    private lateinit var contenedorCuenta: LinearLayout
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExplorarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configura el RecyclerView con un GridLayoutManager
        val gridLayoutManager = GridLayoutManager(this@Explorar, 1)
        binding.recyclerViewDestino.layoutManager = gridLayoutManager

        // Crea un AlertDialog para mostrar el progreso de la carga de datos
        val builder = AlertDialog.Builder(this@Explorar)
        builder.setCancelable(false)
        builder.setView(R.layout.progreso_layout)
        val dialog = builder.create()
        dialog.show()

        // Inicializa la lista de datos y el adaptador
        dataList = ArrayList()
        adapter = AdaptadorDestino(this@Explorar, dataList)
        binding.recyclerViewDestino.adapter = adapter

        // Obtiene la referencia a la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("destinos")

        // Agrega un ValueEventListener para escuchar los cambios en la base de datos
        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                // Limpia la lista de datos actual y carga los datos de la base de datos
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(DatoDestino::class.java)
                    if (dataClass != null) {
                        dataList.add(dataClass)
                    }
                }

                // Actualiza el adaptador y oculta el AlertDialog
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })

        // Configura el listener para la SearchView
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Filtra la lista de destinos basada en el texto de búsqueda
                listaExplorar(newText)
                return true
            }
        })

        // Inicializa los LinearLayouts que funcionan como botones
        contenedorInicio = findViewById(R.id.container_inicio)
        contenedorItinerario = findViewById(R.id.container_itinerario)
        contenedorCuenta = findViewById(R.id.container_cuenta)
        setupListeners()
    }

    /**
     * Filtra la lista de destinos y actualiza el adaptador con los resultados.
     *
     * @param text El texto de búsqueda ingresado por el usuario.
     */
    fun listaExplorar(text: String) {
        val searchList = java.util.ArrayList<DatoDestino>()
        for (dataClass in dataList) {
            if (dataClass.nombre?.lowercase(Locale.getDefault())
                    ?.contains(text.lowercase(Locale.getDefault())) == true
            ) {
                searchList.add(dataClass)
            }
        }
        adapter.searchDataList(searchList)
    }

    /**
     * Configura los listeners para los LinearLayouts que funcionan como botones.
     */
    private fun setupListeners() {
        contenedorInicio.setOnClickListener {
            // Navegar a la pantalla de inicio
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }

        contenedorItinerario.setOnClickListener {
            // Navegar a la pantalla de itinerario
            val intent = Intent(this, Itinerario::class.java)
            startActivity(intent)
        }

        contenedorCuenta.setOnClickListener {
            // Navegar a la pantalla de cuenta
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
}