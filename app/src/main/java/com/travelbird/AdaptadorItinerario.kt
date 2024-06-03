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

class AdaptadorItinerario(
    private val itinerarioList: MutableList<DatoItinerario>,
    private val context: Context,
    private val onEditClickListener: (DatoItinerario) -> Unit,
    private val onDeleteClickListener: (DatoItinerario) -> Unit
) : RecyclerView.Adapter<AdaptadorItinerario.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenDestino: ImageView = itemView.findViewById(R.id.imagenDestino)
        val nombreDestino: TextView = itemView.findViewById(R.id.nombreDestino)
        val fechaHoraDestino: TextView = itemView.findViewById(R.id.fechaHoraDestino)
        val botonEditar: Button = itemView.findViewById(R.id.botonEditar)
        val botonEliminar: Button = itemView.findViewById(R.id.botonEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_itinerario, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destino = itinerarioList[position]

        // Cargar la imagen del destino
        if (destino.imagen != null) {
            Glide.with(holder.itemView.context).load(destino.imagen).into(holder.imagenDestino)
        }

        holder.nombreDestino.text = destino.nombre
        holder.fechaHoraDestino.text = "${destino.fecha} ${destino.hora}"

        holder.botonEditar.setOnClickListener {
            onEditClickListener.invoke(destino)
        }

        holder.botonEliminar.setOnClickListener {
            onDeleteClickListener.invoke(destino)
        }
    }

    override fun getItemCount(): Int {
        return itinerarioList.size
    }
}