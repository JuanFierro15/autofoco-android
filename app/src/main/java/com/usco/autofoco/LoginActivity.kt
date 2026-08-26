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

        binding.btnEntrar.setOnClickListener { intentarEntrar() }
        binding.tvIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    private fun intentarEntrar() {
        binding.tilCorreo.error = null
        binding.tilContrasena.error = null

        val correo = binding.etCorreo.text?.toString().orEmpty()
        val contrasena = binding.etContrasena.text?.toString().orEmpty()

        val correoVacio = esCampoVacio(correo)
        val contrasenaVacia = esCampoVacio(contrasena)

        if (correoVacio || contrasenaVacia) {
            if (correoVacio) binding.tilCorreo.error = getString(R.string.error_campo_obligatorio)
            if (contrasenaVacia) binding.tilContrasena.error = getString(R.string.error_campo_obligatorio)
            Toast.makeText(this, R.string.error_campos_vacios, Toast.LENGTH_SHORT).show()
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

        Toast.makeText(this, R.string.toast_bienvenida_login, Toast.LENGTH_SHORT).show()

        val usuario = correo.substringBefore("@")
        val intent = Intent(this, PrincipalActivity::class.java)
        intent.putExtra(Extras.EXTRA_USUARIO, usuario)
        intent.putExtra(Extras.EXTRA_RECORDARME, binding.swRecordarme.isChecked)
        intent.putExtra(Extras.EXTRA_ORIGEN, Extras.ORIGEN_LOGIN)
        startActivity(intent)
        finish()
    }
}
