package com.travelbird

class DatoDestino {

    var id: String? = null
    var nombre: String? = null
    var ciudad: String? = null
    var pais: String? = null
    var descripcion: String? = null
    var imagen: String? = null
    var atracciones: List<DatoAtraccion>? = null
    var eventos: List<DatoEvento>? = null
    var restaurantes: List<DatoRestaurante>? = null

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

    constructor(){

    }
}