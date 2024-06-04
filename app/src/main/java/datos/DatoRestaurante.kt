package datos

/**
 * Clase de datos que representa un restaurante.
 */
class DatoRestaurante {

    /**
     * Identificador único del restaurante.
     */
    var id: String? = null

    /**
     * Nombre del restaurante.
     */
    var nombre: String? = null

    /**
     * Descripción del restaurante.
     */
    var descripcion: String? = null

    /**
     * URL de la imagen del restaurante.
     */
    var imagen: String? = null

    /**
     * Precio estimado del restaurante.
     */
    var precioEstimado: Double? = null

    /**
     * Ubicación del restaurante.
     */
    var ubicacion: String? = null

    /**
     * Lista de categorías a las que pertenece el restaurante.
     */
    var categorias: List<String>? = null

    /**
     * Constructor principal de la clase DatoRestaurante.
     *
     * @param id Identificador único del restaurante.
     * @param nombre Nombre del restaurante.
     * @param descripcion Descripción del restaurante.
     * @param imagen URL de la imagen del restaurante.
     * @param precioEstimado Precio estimado del restaurante.
     * @param ubicacion Ubicación del restaurante.
     * @param categorias Lista de categorías a las que pertenece el restaurante.
     */
    constructor(
        id: String?,
        nombre: String?,
        descripcion: String?,
        imagen: String?,
        precioEstimado: Double?,
        ubicacion: String?,
        categorias: List<String>?
    ) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.imagen = imagen
        this.precioEstimado = precioEstimado
        this.ubicacion = ubicacion
        this.categorias = categorias
    }

    /**
     * Constructor secundario para crear un objeto vacío de DatoRestaurante.
     */
    constructor(){

    }
}