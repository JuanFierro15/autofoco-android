package com.usco.autofoco

import android.util.Patterns

fun esCampoVacio(texto: String): Boolean = texto.isBlank()

fun esCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

fun esContrasenaValida(contrasena: String): Boolean = contrasena.length >= 6

fun esUsuarioValido(usuario: String): Boolean = usuario.length >= 3

fun contrasenasCoinciden(contrasena: String, confirmacion: String): Boolean = contrasena == confirmacion
