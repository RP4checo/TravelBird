package com.travelbird

import android.content.SharedPreferences
import android.content.Context
class GestorSesion(val context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun saveUser(datoUsuario: DatoUsuario) {
        editor.putString("id", datoUsuario.id)
        editor.putString("nombre", datoUsuario.nombre)
        editor.putString("correo", datoUsuario.correo) // Guarda el correo electrónico
        editor.putString("telefono", datoUsuario.telefono)
        editor.apply()
    }

    fun getUser(): DatoUsuario? {
        val id = prefs.getString("id", null)
        val nombre = prefs.getString("nombre", null)
        val correo = prefs.getString("correo", null)
        val telefono = prefs.getString("telefono", null)
        // ... recupera otros datos
        return if (id != null && nombre != null && correo != null) {
            DatoUsuario(id, nombre, correo, null, telefono, null, null, null, null)
        } else {
            null
        }
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}