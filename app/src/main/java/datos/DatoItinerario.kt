package datos

/**
 * Clase de datos que representa un itinerario.
 */
class DatoItinerario {

    /**
     * Identificador único del itinerario.
     */
    var id: String? = null

    /**
     * Nombre del itinerario.
     */
    var nombre: String? = null

    /**
     * ID del usuario al que pertenece el itinerario.
     */
    var userId: String? = null

    /**
     * Fecha del itinerario.
     */
    var fecha: String? = null

    /**
     * Hora del itinerario.
     */
    var hora: String? = null

    /**
     * URL de la imagen del itinerario.
     */
    var imagen: String? = null

    /**
     * Constructor principal de la clase DatoItinerario.
     *
     * @param id Identificador único del itinerario.
     * @param nombre Nombre del itinerario.
     * @param userId ID del usuario al que pertenece el itinerario.
     * @param fecha Fecha del itinerario.
     * @param hora Hora del itinerario.
     * @param imagen URL de la imagen del itinerario.
     */
    constructor(
        id: String?,
        nombre: String?,
        userId: String?,
        fecha: String?,
        hora: String?,
        imagen: String? = null
    ) {
        this.id = id
        this.nombre = nombre
        this.userId = userId
        this.fecha = fecha
        this.hora = hora
        this.imagen = imagen
    }

    /**
     * Constructor secundario para crear un objeto vacío de DatoItinerario.
     */
    constructor(){

    }
}