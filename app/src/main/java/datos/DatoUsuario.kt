package datos

/**
 * Clase de datos que representa un usuario.
 */
class DatoUsuario {

    /**
     * Identificador único del usuario.
     */
    var id: String? = null

    /**
     * Nombre completo del usuario.
     */
    var nombre: String? = null

    /**
     * Dirección de correo electrónico del usuario.
     */
    var correo: String? = null

    /**
     * Nombre de usuario del usuario.
     */
    var nombreUsuario: String? = null

    /**
     * Contraseña del usuario.
     */
    var contrasena: String? = null

    /**
     * URL de la foto de perfil del usuario.
     */
    var fotoPerfil: String? = null

    /**
     * Lista de identificadores de destinos favoritos del usuario.
     */
    var destinosFavoritos: List<String>? = null

    /**
     * Lista de identificadores de viajes realizados por el usuario.
     */
    var historialViajes: List<String>? = null

    /**
     * Constructor principal de la clase DatoUsuario.
     *
     * @param id Identificador único del usuario.
     * @param nombre Nombre completo del usuario.
     * @param correo Dirección de correo electrónico del usuario.
     * @param nombreUsuario Nombre de usuario del usuario.
     * @param contrasena Contraseña del usuario.
     * @param fotoPerfil URL de la foto de perfil del usuario.
     * @param destinosFavoritos Lista de identificadores de destinos favoritos del usuario.
     * @param historialViajes Lista de identificadores de viajes realizados por el usuario.
     */
    constructor(
        id: String? = null,
        nombre: String? = null,
        correo: String? = null,
        nombreUsuario: String? = null,
        contrasena: String? = null,
        fotoPerfil: String? = null,
        destinosFavoritos: List<String>? = null,
        historialViajes: List<String>? = null
    ) {
        this.id = id
        this.nombre = nombre
        this.correo = correo
        this.nombreUsuario = nombreUsuario
        this.contrasena = contrasena
        this.fotoPerfil = fotoPerfil
        this.destinosFavoritos = destinosFavoritos
        this.historialViajes = historialViajes
    }
}