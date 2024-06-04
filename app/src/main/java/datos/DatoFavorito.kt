package datos

/**
 * Clase de datos que representa un favorito.
 */
class DatoFavorito {

    /**
     * Identificador único del favorito.
     */
    var id: String? = null

    /**
     * Nombre del favorito.
     */
    var nombre: String? = null

    /**
     * URL de la imagen del favorito.
     */
    var imagen: String? = null

    /**
     * Categoría del favorito.
     */
    var categoria: String? = null

    /**
     * Constructor principal de la clase DatoFavorito.
     *
     * @param id Identificador único del favorito.
     * @param nombre Nombre del favorito.
     * @param imagen URL de la imagen del favorito.
     * @param categoria Categoría del favorito.
     */
    constructor(id: String?, nombre: String?, imagen: String?, categoria: String?) {
        this.id = id
        this.nombre = nombre
        this.imagen = imagen
        this.categoria = categoria
    }

    /**
     * Constructor secundario para crear un objeto vacío de DatoFavorito.
     */
    constructor(){

    }
}