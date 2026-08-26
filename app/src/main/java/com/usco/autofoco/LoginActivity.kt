package com.usco.autofoco

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.usco.autofoco.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonEntrar.setOnClickListener { intentarEntrar() }
        binding.textIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    private fun intentarEntrar() {
        binding.inputLayoutCorreo.error = null
        binding.inputLayoutContrasena.error = null

        val correo = binding.editTextCorreo.text?.toString().orEmpty()
        val contrasena = binding.editTextContrasena.text?.toString().orEmpty()

        val correoVacio = esCampoVacio(correo)
        val contrasenaVacia = esCampoVacio(contrasena)

        if (correoVacio || contrasenaVacia) {
            if (correoVacio) binding.inputLayoutCorreo.error = getString(R.string.error_campo_obligatorio)
            if (contrasenaVacia) binding.inputLayoutContrasena.error = getString(R.string.error_campo_obligatorio)
            Toast.makeText(this, R.string.error_campos_vacios, Toast.LENGTH_SHORT).show()
            return
        }

        if (!esCorreoValido(correo)) {
            binding.inputLayoutCorreo.error = getString(R.string.error_correo_invalido)
            Toast.makeText(this, R.string.error_correo_invalido, Toast.LENGTH_SHORT).show()
            return
        }

        if (!esContrasenaValida(contrasena)) {
            binding.inputLayoutContrasena.error = getString(R.string.error_contrasena_corta)
            Toast.makeText(this, R.string.error_contrasena_corta, Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, R.string.toast_bienvenida_login, Toast.LENGTH_SHORT).show()

        val usuario = correo.substringBefore("@")
        val intent = Intent(this, PrincipalActivity::class.java).apply {
            putExtra(Extras.EXTRA_USUARIO, usuario)
            putExtra(Extras.EXTRA_RECORDARME, binding.switchRecordarme.isChecked)
            putExtra(Extras.EXTRA_ORIGEN, Extras.ORIGEN_LOGIN)
        }
        startActivity(intent)
        finish()
    }
}
