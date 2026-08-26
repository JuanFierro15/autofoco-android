package com.usco.autofoco

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.usco.autofoco.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearCuenta.setOnClickListener { intentarCrearCuenta() }
        binding.tvIrLogin.setOnClickListener { finish() }
    }

    private fun intentarCrearCuenta() {
        limpiarErrores()

        val nombre = binding.etNombre.text?.toString().orEmpty()
        val usuario = binding.etUsuario.text?.toString().orEmpty()
        val correo = binding.etCorreo.text?.toString().orEmpty()
        val contrasena = binding.etContrasena.text?.toString().orEmpty()
        val confirmarContrasena = binding.etConfirmarContrasena.text?.toString().orEmpty()
        val vehiculo = binding.etVehiculo.text?.toString().orEmpty()

        val nombreVacio = esCampoVacio(nombre)
        val usuarioVacio = esCampoVacio(usuario)
        val correoVacio = esCampoVacio(correo)
        val contrasenaVacia = esCampoVacio(contrasena)
        val confirmarVacia = esCampoVacio(confirmarContrasena)
        val vehiculoVacio = esCampoVacio(vehiculo)

        if (nombreVacio || usuarioVacio || correoVacio || contrasenaVacia || confirmarVacia || vehiculoVacio) {
            if (nombreVacio) binding.tilNombre.error = getString(R.string.error_campo_obligatorio)
            if (usuarioVacio) binding.tilUsuario.error = getString(R.string.error_campo_obligatorio)
            if (correoVacio) binding.tilCorreo.error = getString(R.string.error_campo_obligatorio)
            if (contrasenaVacia) binding.tilContrasena.error = getString(R.string.error_campo_obligatorio)
            if (confirmarVacia) binding.tilConfirmarContrasena.error = getString(R.string.error_campo_obligatorio)
            if (vehiculoVacio) binding.tilVehiculo.error = getString(R.string.error_campo_obligatorio)
            Toast.makeText(this, R.string.error_campos_vacios, Toast.LENGTH_SHORT).show()
            return
        }

        if (!esUsuarioValido(usuario)) {
            binding.tilUsuario.error = getString(R.string.error_usuario_corto)
            Toast.makeText(this, R.string.error_usuario_corto, Toast.LENGTH_SHORT).show()
            return
        }

        if (!esCorreoValido(correo)) {
            binding.tilCorreo.error = getString(R.string.error_correo_invalido)
            Toast.makeText(this, R.string.error_correo_invalido, Toast.LENGTH_SHORT).show()
            return
        }

        if (!esContrasenaValida(contrasena)) {
            binding.tilContrasena.error = getString(R.string.error_contrasena_corta)
            Toast.makeText(this, R.string.error_contrasena_corta, Toast.LENGTH_SHORT).show()
            return
        }

        if (!contrasenasCoinciden(contrasena, confirmarContrasena)) {
            binding.tilConfirmarContrasena.error = getString(R.string.error_contrasenas_no_coinciden)
            Toast.makeText(this, R.string.error_contrasenas_no_coinciden, Toast.LENGTH_SHORT).show()
            return
        }

        val tipoVehiculo = when {
            binding.rbCarro.isChecked -> binding.rbCarro.text.toString()
            binding.rbMoto.isChecked -> binding.rbMoto.text.toString()
            binding.rbAmbos.isChecked -> binding.rbAmbos.text.toString()
            else -> null
        }

        if (tipoVehiculo == null) {
            Toast.makeText(this, R.string.error_selecciona_manejo, Toast.LENGTH_SHORT).show()
            return
        }

        if (!binding.cbTerminos.isChecked) {
            Toast.makeText(this, R.string.error_acepta_terminos, Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, R.string.toast_cuenta_creada, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, PrincipalActivity::class.java)
        intent.putExtra(Extras.EXTRA_NOMBRE, nombre)
        intent.putExtra(Extras.EXTRA_USUARIO, usuario)
        intent.putExtra(Extras.EXTRA_CORREO, correo)
        intent.putExtra(Extras.EXTRA_VEHICULO, vehiculo)
        intent.putExtra(Extras.EXTRA_TIPO_VEHICULO, tipoVehiculo)
        intent.putExtra(Extras.EXTRA_ORIGEN, Extras.ORIGEN_REGISTRO)
        startActivity(intent)
        finish()
    }

    private fun limpiarErrores() {
        binding.tilNombre.error = null
        binding.tilUsuario.error = null
        binding.tilCorreo.error = null
        binding.tilContrasena.error = null
        binding.tilConfirmarContrasena.error = null
        binding.tilVehiculo.error = null
    }
}
