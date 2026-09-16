package com.usco.autofoco.model

data class Publicacion(
    val usuario: String,
    val vehiculo: String,
    val marca: String,
    val anio: String,
    val modificacion: String,
    val caption: String,
    val fotoResId: Int,
    var likes: Int,
    var meGusta: Boolean = false
)
