package com.travelbird

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.travelbird.databinding.FavoritosBinding

class Favoritos : AppCompatActivity() {

    private lateinit var contenedorCerrar: ImageView
    private lateinit var botonListo: Button
    private lateinit var binding: FavoritosBinding
    private lateinit var database: DatabaseReference
    private lateinit var favoritosAdapter: AdaptadorFavorito
    private lateinit var favoritosList: MutableList<DatoFavorito>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contenedorCerrar = findViewById(R.id.container_close)
        botonListo = findViewById(R.id.boton_listo)

        // Inicializar Firebase Database
        database = FirebaseDatabase.getInstance().getReference("favoritos")

        // Inicializar la lista de favoritos
        favoritosList = mutableListOf()

        // Inicializar el adaptador del RecyclerView
        favoritosAdapter = AdaptadorFavorito(favoritosList, this,
            onDeleteClickListener = { favorito ->
                eliminarFavorito(favorito)
            }
        )

        // Configurar el RecyclerView
        binding.recyclerViewDestino.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDestino.adapter = favoritosAdapter

        // Obtener el ID del usuario actual
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        // Escuchar los cambios en la colección de favoritos del usuario
        if (userId != null) {
            database.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    favoritosList.clear()
                    for (itemSnapshot in snapshot.children) {
                        val favoritoItem = itemSnapshot.getValue(DatoFavorito::class.java)
                        if (favoritoItem != null) {
                            // Asignar el ID del favorito desde la clave del snapshot
                            favoritoItem.id = itemSnapshot.key
                            favoritosList.add(favoritoItem)
                        }
                    }
                    favoritosAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Favoritos, "Error al obtener los favoritos", Toast.LENGTH_SHORT).show()
                }
            })
        }

        setupListeners()
    }

    private fun setupListeners() {
        contenedorCerrar.setOnClickListener {
            navegarAExplorar()
        }

        botonListo.setOnClickListener {
            navegarAExplorar()
        }
    }

    private fun navegarAExplorar() {
        val intent = Intent(this, Explorar::class.java)
        startActivity(intent)
        finish()
    }

    private fun eliminarFavorito(favorito: DatoFavorito) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val favoritoRef = favorito.id?.let { database.child(userId).child(it) }
            if (favoritoRef != null) {
                favoritoRef.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Favorito eliminado", Toast.LENGTH_SHORT).show()
                        favoritosList.remove(favorito)
                        favoritosAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this, "Error al eliminar favorito", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}