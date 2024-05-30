package com.travelbird

class DatoEvento {
    var id: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var imagen: String? = null
    var precioEstimado: Int? = null
    var ubicacion: String? = null
    var categorias: List<String>? = null
    var fecha: String? = null
    var hora: String? = null

    constructor(
        id: String?,
        nombre: String?,
        descripcion: String?,
        imagen: String?,
        precioEstimado: Int?,
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

    constructor(){

    }
}