package datos
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
import logica.Destino
import com.travelbird.R

/**
 * Adaptador para el RecyclerView que muestra una lista de destinos.
 *
 * @param context Contexto de la aplicación.
 * @param dataList Lista de datos de destino.
 */
class AdaptadorDestino(private val context: Context, private var dataList: List<DatoDestino>) : RecyclerView.Adapter<MyViewHolderDestino>() {

    /**
     * Crea un nuevo ViewHolder para el RecyclerView.
     *
     * @param parent ViewGroup padre del ViewHolder.
     * @param viewType Tipo de vista del ViewHolder.
     * @return Un nuevo ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDestino {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolderDestino(view)
    }

    /**
     * Enlaza los datos del destino al ViewHolder.
     *
     * @param holder ViewHolder que se va a enlazar.
     * @param position Posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: MyViewHolderDestino, position: Int) {
        Glide.with(context).load(dataList[position].imagen).into(holder.recImage)
        holder.recNombre.text = dataList[position].nombre

        /**
         * Configura un OnClickListener para el CardView que abre la actividad de detalles del destino.
         */
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

    /**
     * Devuelve el tamaño de la lista de datos.
     *
     * @return Tamaño de la lista.
     */
    override fun getItemCount(): Int {
        return dataList.size
    }

    /**
     * Actualiza la lista de datos con una nueva lista y notifica al RecyclerView.
     *
     * @param searchList Nueva lista de datos de destino.
     */
    fun searchDataList(searchList: List<DatoDestino>) {
        dataList = searchList
        notifyDataSetChanged()
    }

}

/**
 * ViewHolder para el RecyclerView que muestra un destino.
 *
 * @param itemView Vista del ViewHolder.
 */
class MyViewHolderDestino(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * ImageView que muestra la imagen del destino.
     */
    var recImage: ImageView

    /**
     * TextView que muestra el nombre del destino.
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