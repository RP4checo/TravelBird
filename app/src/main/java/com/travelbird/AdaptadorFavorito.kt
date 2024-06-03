package com.travelbird

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson

class AdaptadorFavorito(
    private val favoritosList: MutableList<DatoFavorito>, // Lista de objetos DatoFavorito
    private val context: Context,
    private val onDeleteClickListener: (DatoFavorito) -> Unit // Listener para eliminar item
) : RecyclerView.Adapter<AdaptadorFavorito.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenFavorito: ImageView = itemView.findViewById(R.id.imagenFavorito)
        val nombreFavorito: TextView = itemView.findViewById(R.id.nombreFavorito)
        val categoriaFavorito: TextView = itemView.findViewById(R.id.categoriaFavorito)
        val botonEliminar: Button = itemView.findViewById(R.id.botonEliminarFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorito = favoritosList[position]

        // Cargar la imagen del favorito
        if (favorito.imagen != null) {
            Glide.with(holder.itemView.context).load(favorito.imagen).into(holder.imagenFavorito)
        }

        holder.nombreFavorito.text = favorito.nombre
        holder.categoriaFavorito.text = favorito.categoria

        holder.botonEliminar.setOnClickListener {
            onDeleteClickListener.invoke(favorito)
        }

    }

    override fun getItemCount(): Int {
        return favoritosList.size
    }
}