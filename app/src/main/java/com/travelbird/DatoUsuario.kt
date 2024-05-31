package com.travelbird

class DatoUsuario {

    var id: String? = null
    var nombre: String? = null
    var correo: String? = null
    var contrasena: String? = null
    var telefono: String? = null
    var fechaNacimiento: String? = null
    var fotoPerfil: String? = null
    var destinosFavoritos: List<String>? = null
    var historialViajes: List<String>? = null

    constructor(
        id: String?,
        nombre: String?,
        correo: String?,
        contrasena: String?,
        telefono: String?,
        fechaNacimiento: String?,
        fotoPerfil: String?,
        destinosFavoritos: List<String>?,
        historialViajes: List<String>?
    ) {
        this.id = id
        this.nombre = nombre
        this.correo = correo
        this.contrasena = contrasena
        this.telefono = telefono
        this.fechaNacimiento = fechaNacimiento
        this.fotoPerfil = fotoPerfil
        this.destinosFavoritos = destinosFavoritos
        this.historialViajes = historialViajes
    }



}