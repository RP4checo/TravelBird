package datos

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
import logica.Detalles
import com.travelbird.R

/**
 * Adaptador para el RecyclerView que muestra una lista de eventos.
 *
 * @param context Contexto de la aplicación.
 * @param dataList Lista de datos de evento.
 */
class AdaptadorEvento (private val context: Context, private var dataList: List<DatoEvento>) : RecyclerView.Adapter<MyViewHolderEvento>() {

    /**
     * Crea un nuevo ViewHolder para el RecyclerView.
     *
     * @param parent ViewGroup padre del ViewHolder.
     * @param viewType Tipo de vista del ViewHolder.
     * @return Un nuevo ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderEvento {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolderEvento(view)
    }

    /**
     * Enlaza los datos del evento al ViewHolder.
     *
     * @param holder ViewHolder que se va a enlazar.
     * @param position Posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: MyViewHolderEvento, position: Int) {
        Glide.with(context).load(dataList[position].imagen).into(holder.recImage)
        holder.recNombre.text = dataList[position].nombre

        /**
         * Configura un OnClickListener para el CardView que abre la actividad de detalles del evento.
         */
        holder.recCard.setOnClickListener {
            val intent = Intent(context, Detalles::class.java)
            intent.putExtra("tipo", "evento") // Especificar el tipo de dato
            intent.putExtra("id", dataList[holder.adapterPosition].id)
            intent.putExtra("nombre", dataList[holder.adapterPosition].nombre)
            intent.putExtra("descripcion", dataList[holder.adapterPosition].descripcion)
            intent.putExtra("imagen", dataList[holder.adapterPosition].imagen)
            intent.putExtra("precio", dataList[holder.adapterPosition].precioEstimado)
            intent.putExtra("ubicacion", dataList[holder.adapterPosition].ubicacion)
            intent.putExtra("categorias", dataList[holder.adapterPosition].categorias?.toTypedArray())
            intent.putExtra("fecha", dataList[holder.adapterPosition].fecha)
            intent.putExtra("hora", dataList[holder.adapterPosition].hora)
            context.startActivity(intent)
        }
    }

    /**
     * Devuelve el tamaño de la lista de datos.
     *
     * @return Tamaño de la lista.
     */
    override fun getItemCount(): Int {
        return dataList.size
    }

}

/**
 * ViewHolder para el RecyclerView que muestra un evento.
 *
 * @param itemView Vista del ViewHolder.
 */
class MyViewHolderEvento(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * ImageView que muestra la imagen del evento.
     */
    var recImage: ImageView

    /**
     * TextView que muestra el nombre del evento.
     */
    var recNombre: TextView

    /**
     * CardView que contiene los elementos de la vista.
     */
    var recCard: CardView

    init {
        recImage = itemView.findViewById(R.id.recImage)
        recNombre = itemView.findViewById(R.id.recNombre)
        recCard = itemView.findViewById(R.id.recCard)
    }
}