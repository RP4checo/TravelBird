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
 * Adaptador para el RecyclerView que muestra una lista de favoritos.
 *
 * @param favoritosList Lista mutable de datos de favoritos.
 * @param context Contexto de la aplicación.
 * @param onDeleteClickListener Función que se ejecuta cuando se presiona el botón de eliminar.
 */
class AdaptadorFavorito(
    private val favoritosList: MutableList<DatoFavorito>,
    private val context: Context,
    private val onDeleteClickListener: (DatoFavorito) -> Unit
) : RecyclerView.Adapter<AdaptadorFavorito.ViewHolder>() {

    /**
     * Clase ViewHolder para el RecyclerView.
     *
     * @param itemView Vista del ViewHolder.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * ImageView que muestra la imagen del favorito.
         */
        val imagenFavorito: ImageView = itemView.findViewById(R.id.imagenFavorito)

        /**
         * TextView que muestra el nombre del favorito.
         */
        val nombreFavorito: TextView = itemView.findViewById(R.id.nombreFavorito)

        /**
         * TextView que muestra la categoría del favorito.
         */
        val categoriaFavorito: TextView = itemView.findViewById(R.id.categoriaFavorito)

        /**
         * Button que elimina el favorito.
         */
        val botonEliminar: Button = itemView.findViewById(R.id.botonEliminarFavorito)
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
            .inflate(R.layout.item_favorito, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Enlaza los datos del favorito al ViewHolder.
     *
     * @param holder ViewHolder que se va a enlazar.
     * @param position Posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorito = favoritosList[position]

        if (favorito.imagen != null) {
            Glide.with(holder.itemView.context).load(favorito.imagen).into(holder.imagenFavorito)
        }

        holder.nombreFavorito.text = favorito.nombre
        holder.categoriaFavorito.text = favorito.categoria

        holder.botonEliminar.setOnClickListener {
            onDeleteClickListener.invoke(favorito)
        }

    }

    /**
     * Devuelve el tamaño de la lista de datos.
     *
     * @return Tamaño de la lista.
     */
    override fun getItemCount(): Int {
        return favoritosList.size
    }
}