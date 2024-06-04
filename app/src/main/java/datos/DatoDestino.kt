package datos

/**
 * Clase de datos que representa un destino turístico.
 */
class DatoDestino {

    /**
     * Identificador único del destino.
     */
    var id: String? = null

    /**
     * Nombre del destino.
     */
    var nombre: String? = null

    /**
     * Ciudad del destino.
     */
    var ciudad: String? = null

    /**
     * País del destino.
     */
    var pais: String? = null

    /**
     * Descripción del destino.
     */
    var descripcion: String? = null

    /**
     * URL de la imagen del destino.
     */
    var imagen: String? = null

    /**
     * Lista de atracciones del destino.
     */
    var atracciones: List<DatoAtraccion>? = null

    /**
     * Lista de eventos del destino.
     */
    var eventos: List<DatoEvento>? = null

    /**
     * Lista de restaurantes del destino.
     */
    var restaurantes: List<DatoRestaurante>? = null

    /**
     * Constructor principal de la clase DatoDestino.
     *
     * @param id Identificador único del destino.
     * @param nombre Nombre del destino.
     * @param ciudad Ciudad del destino.
     * @param pais País del destino.
     * @param descripcion Descripción del destino.
     * @param imagen URL de la imagen del destino.
     * @param atracciones Lista de atracciones del destino.
     * @param eventos Lista de eventos del destino.
     * @param restaurantes Lista de restaurantes del destino.
     */
    constructor(
        id: String?,
        nombre: String?,
        ciudad: String?,
        pais: String?,
        descripcion: String?,
        imagen: String?,
        atracciones: List<DatoAtraccion>?,
        eventos: List<DatoEvento>?,
        restaurantes: List<DatoRestaurante>?
    ) {
        this.id = id
        this.nombre = nombre
        this.ciudad = ciudad
        this.pais = pais
        this.descripcion = descripcion
        this.imagen = imagen
        this.atracciones = atracciones
        this.eventos = eventos
        this.restaurantes = restaurantes
    }

    /**
     * Constructor secundario para crear un objeto vacío de DatoDestino.
     */
    constructor(){

    }
}