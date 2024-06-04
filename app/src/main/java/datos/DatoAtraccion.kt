package datos

/**
 * Clase de datos que representa una atracción.
 */
class DatoAtraccion {
    /**
     * Identificador único de la atracción.
     */
    var id: String? = null

    /**
     * Nombre de la atracción.
     */
    var nombre: String? = null

    /**
     * Descripción de la atracción.
     */
    var descripcion: String? = null

    /**
     * URL de la imagen de la atracción.
     */
    var imagen: String? = null

    /**
     * Precio estimado de la atracción.
     */
    var precioEstimado: Double? = null

    /**
     * Ubicación de la atracción.
     */
    var ubicacion: String? = null

    /**
     * Lista de categorías a las que pertenece la atracción.
     */
    var categorias: List<String>? = null

    /**
     * Constructor principal de la clase DatoAtraccion.
     *
     * @param id Identificador único de la atracción.
     * @param nombre Nombre de la atracción.
     * @param descripcion Descripción de la atracción.
     * @param imagen URL de la imagen de la atracción.
     * @param precioEstimado Precio estimado de la atracción.
     * @param ubicacion Ubicación de la atracción.
     * @param categorias Lista de categorías a las que pertenece la atracción.
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
     * Constructor secundario para crear un objeto vacío de DatoAtraccion.
     */
    constructor(){

    }
}