package com.travelbird

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

class AdaptadorAtraccion(private val context: Context, private var dataList: List<DatoAtraccion>) : RecyclerView.Adapter<MyViewHolderAtraccion>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderAtraccion {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolderAtraccion(view)
    }

    override fun onBindViewHolder(holder: MyViewHolderAtraccion, position: Int) {
        Glide.with(context).load(dataList[position].imagen).into(holder.recImage)
        holder.recNombre.text = dataList[position].nombre

        holder.recCard.setOnClickListener {
            val intent = Intent(context, Detalles::class.java)
            // Pasar información del objeto DatoAtraccion al intent
            intent.putExtra("tipo", "atraccion") // Especificar el tipo de dato
            intent.putExtra("id", dataList[holder.adapterPosition].id)
            intent.putExtra("nombre", dataList[holder.adapterPosition].nombre)
            intent.putExtra("descripcion", dataList[holder.adapterPosition].descripcion)
            intent.putExtra("imagen", dataList[holder.adapterPosition].imagen)
            intent.putExtra("precio", dataList[holder.adapterPosition].precioEstimado)
            intent.putExtra("ubicacion", dataList[holder.adapterPosition].ubicacion)
            intent.putExtra("categorias", dataList[holder.adapterPosition].categorias?.toTypedArray())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class MyViewHolderAtraccion(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recImage: ImageView
    var recNombre: TextView
    var recCard: CardView

    init {
        recImage = itemView.findViewById(R.id.recImage)
        recNombre = itemView.findViewById(R.id.recNombre)
        recCard = itemView.findViewById(R.id.recCard)
    }
}