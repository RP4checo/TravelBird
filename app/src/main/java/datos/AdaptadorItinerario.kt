package datos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.travelbird.R

/**
 * Adaptador para el RecyclerView que muestra una lista de destinos en un itinerario.
 *
 * @param itinerarioList Lista mutable de datos de destinos en un itinerario.
 * @param context Contexto de la aplicación.
 * @param onEditClickListener Función que se ejecuta cuando se presiona el botón de editar.
 * @param onDeleteClickListener Función que se ejecuta cuando se presiona el botón de eliminar.
 */
class AdaptadorItinerario(
    private val itinerarioList: MutableList<DatoItinerario>,
    private val context: Context,
    private val onEditClickListener: (DatoItinerario) -> Unit,
    private val onDeleteClickListener: (DatoItinerario) -> Unit
) : RecyclerView.Adapter<AdaptadorItinerario.ViewHolder>() {

    /**
     * Clase ViewHolder para el RecyclerView.
     *
     * @param itemView Vista del ViewHolder.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * ImageView que muestra la imagen del destino.
         */
        val imagenDestino: ImageView = itemView.findViewById(R.id.imagenDestino)

        /**
         * TextView que muestra el nombre del destino.
         */
        val nombreDestino: TextView = itemView.findViewById(R.id.nombreDestino)

        /**
         * TextView que muestra la fecha y hora del destino.
         */
        val fechaHoraDestino: TextView = itemView.findViewById(R.id.fechaHoraDestino)

        /**
         * Button que edita el destino en el itinerario.
         */
        val botonEditar: Button = itemView.findViewById(R.id.botonEditar)

        /**
         * Button que elimina el destino del itinerario.
         */
        val botonEliminar: Button = itemView.findViewById(R.id.botonEliminar)
    }

    /**
     * Crea un nuevo ViewHolder para el RecyclerView.
     *
     * @param parent ViewGroup padre del ViewHolder.
     * @param viewType Tipo de vista del ViewHolder.
     * @return Un nuevo ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_itinerario, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Enlaza los datos del destino al ViewHolder.
     *
     * @param holder ViewHolder que se va a enlazar.
     * @param position Posición del elemento en la lista.
     */
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

    /**
     * Devuelve el tamaño de la lista de datos.
     *
     * @return Tamaño de la lista.
     */
    override fun getItemCount(): Int {
        return itinerarioList.size
    }
}