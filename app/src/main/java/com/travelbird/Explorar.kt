package com.travelbird

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.travelbird.databinding.ExplorarBinding
import java.util.Locale

class Explorar : AppCompatActivity() {

    private lateinit var binding: ExplorarBinding
    private lateinit var dataList: ArrayList<DatoDestino>
    private lateinit var adapter: AdaptadorDestino
    var databaseReference:DatabaseReference? = null
    var eventListener: ValueEventListener? = null
    private lateinit var contenedorInicio: LinearLayout
    private lateinit var contenedorItinerario: LinearLayout
    private lateinit var contenedorCuenta: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExplorarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this@Explorar, 1)
        binding.recyclerView.layoutManager = gridLayoutManager
        //binding.search.clearFocus()

        val builder = AlertDialog.Builder(this@Explorar)
        builder.setCancelable(false)
        builder.setView(R.layout.progreso_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = AdaptadorDestino(this@Explorar, dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("destinos")
        dialog.show()

        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(DatoDestino::class.java)
                    if (dataClass != null) {
                        dataList.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }
        })

        contenedorInicio = findViewById(R.id.container_inicio)
        contenedorItinerario = findViewById(R.id.container_itinerario)
        contenedorCuenta = findViewById(R.id.container_cuenta)
        setupListeners()
    }

    fun searchList(text: String) {
        val searchList = java.util.ArrayList<DatoDestino>()
        for (dataClass in dataList) {
            if (dataClass.nombre?.lowercase()
                    ?.contains(text.lowercase(Locale.getDefault())) == true
            ) {
                searchList.add(dataClass)            }
        }
        adapter.searchDataList(searchList)
    }

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
            val intent = Intent(this, Cuenta::class.java)
            startActivity(intent)
        }
    }
}
