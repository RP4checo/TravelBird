package datos

/**
 * Clase de datos que representa un evento.
 */
class DatoEvento {
    /**
     * Identificador único del evento.
     */
    var id: String? = null

    /**
     * Nombre del evento.
     */
    var nombre: String? = null

    /**
     * Descripción del evento.
     */
    var descripcion: String? = null

    /**
     * URL de la imagen del evento.
     */
    var imagen: String? = null

    /**
     * Precio estimado del evento.
     */
    var precioEstimado: Double? = null

    /**
     * Ubicación del evento.
     */
    var ubicacion: String? = null

    /**
     * Lista de categorías a las que pertenece el evento.
     */
    var categorias: List<String>? = null

    /**
     * Fecha del evento.
     */
    var fecha: String? = null

    /**
     * Hora del evento.
     */
    var hora: String? = null

    /**
     * Constructor principal de la clase DatoEvento.
     *
     * @param id Identificador único del evento.
     * @param nombre Nombre del evento.
     * @param descripcion Descripción del evento.
     * @param imagen URL de la imagen del evento.
     * @param precioEstimado Precio estimado del evento.
     * @param ubicacion Ubicación del evento.
     * @param categorias Lista de categorías a las que pertenece el evento.
     * @param fecha Fecha del evento.
     * @param hora Hora del evento.
     */
    constructor(
        id: String?,
        nombre: String?,
        descripcion: String?,
        imagen: String?,
        precioEstimado: Double?,
        ubicacion: String?,
        categorias: List<String>?,
        fecha: String?,
        hora: String?
    ) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.imagen = imagen
        this.precioEstimado = precioEstimado
        this.ubicacion = ubicacion
        this.categorias = categorias
        this.fecha = fecha
        this.hora = hora
    }

    /**
     * Constructor secundario para crear un objeto vacío de DatoEvento.
     */
    constructor(){

    }
}