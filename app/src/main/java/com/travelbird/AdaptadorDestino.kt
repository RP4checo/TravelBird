package com.travelbird
// AdaptadorDestino

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson

class AdaptadorDestino(private val context: Context, private var dataList: List<DatoDestino>) : RecyclerView.Adapter<MyViewHolderDestino>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDestino {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolderDestino(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderDestino, position: Int) {
        Glide.with(context).load(dataList[position].imagen).into(holder.recImage)
        holder.recNombre.text = dataList[position].nombre

        holder.recCard.setOnClickListener {
            val intent = Intent(context, Destino::class.java)
            intent.putExtra("Imagen", dataList[holder.adapterPosition].imagen)
            intent.putExtra("Descripción", dataList[holder.adapterPosition].descripcion)
            intent.putExtra("Nombre", dataList[holder.adapterPosition].nombre)
            intent.putExtra("Pais", dataList[holder.adapterPosition].pais)
            intent.putExtra("Atracciones", Gson().toJson(dataList[holder.adapterPosition].atracciones))
            intent.putExtra("Eventos", Gson().toJson(dataList[holder.adapterPosition].eventos))
            intent.putExtra("Restaurantes", Gson().toJson(dataList[holder.adapterPosition].restaurantes))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun searchDataList(searchList: List<DatoDestino>) {
        dataList = searchList
        notifyDataSetChanged()
    }

}

class MyViewHolderDestino(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recImage: ImageView
    var recNombre: TextView
    var recCard: CardView

    init {
        recImage = itemView.findViewById(R.id.recImage)
        recNombre = itemView.findViewById(R.id.recNombre)
        recCard = itemView.findViewById(R.id.recCard)
    }
}