package com.travelbird

class DatoAtraccion {
    var id: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var imagen: String? = null
    var precioEstimado: Double? = null
    var ubicacion: String? = null
    var categorias: List<String>? = null

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

    constructor(){

    }


}