package com.travelbird

class DatoDestino {

    var id: String? = null
    var nombre: String? = null
    var ciudad: String? = null
    var pais: String? = null
    var descripcion: String? = null
    var imagen: String? = null
    constructor(id: String?, nombre: String?, ciudad: String?, pais: String?, descripcion: String?, imagen: String?){
        this.id = id
        this.nombre = nombre
        this.ciudad = ciudad
        this.pais = pais
        this.descripcion = descripcion
        this.imagen = imagen
    }

    constructor(){

    }
}