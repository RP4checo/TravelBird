package com.travelbird

class DatoItinerario {

    var id: String? = null
    var nombre: String? = null
    var userId: String? = null
    var fecha: String? = null
    var hora: String? = null
    var imagen: String? = null // Agregar atributo imagen

    constructor(
        id: String?,
        nombre: String?,
        userId: String?,
        fecha: String?,
        hora: String?,
        imagen: String? = null // Agregar imagen al constructor
    ) {
        this.id = id
        this.nombre = nombre
        this.userId = userId
        this.fecha = fecha
        this.hora = hora
        this.imagen = imagen // Asignar imagen
    }

    constructor(){

    }
}