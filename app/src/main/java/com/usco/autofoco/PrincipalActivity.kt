package com.usco.autofoco

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.usco.autofoco.adapter.PublicacionAdapter
import com.usco.autofoco.databinding.ActivityPrincipalBinding
import com.usco.autofoco.model.Publicacion

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarBienvenida()
        configurarFeed()
        configurarBottomNav()
    }

    private fun mostrarBienvenida() {
        val origen = intent.getStringExtra(Extras.EXTRA_ORIGEN) ?: Extras.ORIGEN_LOGIN
        val usuario = intent.getStringExtra(Extras.EXTRA_USUARIO)
            ?: getString(R.string.valor_por_defecto_usuario)

        if (origen == Extras.ORIGEN_REGISTRO) {
            val nombre = intent.getStringExtra(Extras.EXTRA_NOMBRE)
                ?: getString(R.string.valor_por_defecto_nombre)
            val vehiculo = intent.getStringExtra(Extras.EXTRA_VEHICULO).orEmpty()
            val tipoVehiculo = intent.getStringExtra(Extras.EXTRA_TIPO_VEHICULO).orEmpty()

            binding.tvSaludo.text = getString(R.string.saludo_bienvenida, nombre)
            binding.tvUsuarioBienvenida.text = getString(R.string.formato_usuario, usuario)
            binding.tvUsuarioBienvenida.visibility = View.VISIBLE
            binding.tvBadgeVehiculo.text = getString(R.string.formato_ficha_bienvenida, tipoVehiculo, vehiculo)
            binding.tvBadgeVehiculo.visibility = View.VISIBLE
            binding.tvSesionRecordada.visibility = View.GONE
        } else {
            val recordarme = intent.getBooleanExtra(Extras.EXTRA_RECORDARME, false)

            binding.tvSaludo.text = getString(R.string.saludo_hola_de_nuevo, usuario)
            binding.tvUsuarioBienvenida.visibility = View.GONE
            binding.tvBadgeVehiculo.visibility = View.GONE
            binding.tvSesionRecordada.visibility = if (recordarme) View.VISIBLE else View.GONE
        }
    }

    private fun configurarFeed() {
        val publicaciones = listOf(
            Publicacion(
                usuario = "carlos_clasicos",
                vehiculo = "Chevrolet Camaro SS 1969",
                marca = "Chevrolet",
                anio = "1969",
                modificacion = "Restauración numbers matching",
                caption = "3 años de restauración y por fin rueda como el primer día.",
                fotoResId = R.drawable.foto_post_clasico_restaurado,
                likes = 214
            ),
            Publicacion(
                usuario = "santi_dostiempos",
                vehiculo = "Yamaha RX 135",
                marca = "Yamaha",
                anio = "1998",
                modificacion = "Escape libre artesanal",
                caption = "Le cambié el escape y ahora suena como debía sonar desde fábrica.",
                fotoResId = R.drawable.foto_post_moto,
                likes = 89
            ),
            Publicacion(
                usuario = "esteban_jdm",
                vehiculo = "Honda Civic EK 1999",
                marca = "Honda",
                anio = "1999",
                modificacion = "Swap B16 + coilovers",
                caption = "El swap quedó perfecto, ya solo falta el mapeo de la ECU.",
                fotoResId = R.drawable.foto_post_carro_modificado,
                likes = 342
            ),
            Publicacion(
                usuario = "taller_delrio",
                vehiculo = "Renault 4 GTL",
                marca = "Renault",
                anio = "1985",
                modificacion = "Reconstrucción completa de motor",
                caption = "Culata, pistones y anillos nuevos. Este motor va a durar otros 30 años.",
                fotoResId = R.drawable.foto_post_motor_desarmado,
                likes = 156
            ),
            Publicacion(
                usuario = "andres_nightdrive",
                vehiculo = "Mazda RX-7 FD",
                marca = "Mazda",
                anio = "1993",
                modificacion = "Kit de luces LED bajo chasis",
                caption = "Las mejores fotos de este carro siempre salen de noche.",
                fotoResId = R.drawable.foto_post_nocturna,
                likes = 278
            ),
            Publicacion(
                usuario = "juanp_motors",
                vehiculo = "Volkswagen Golf GTI Mk5",
                marca = "Volkswagen",
                anio = "2007",
                modificacion = "Rines réplica 18 pulgadas",
                caption = "El detalle que le cambia la cara al carro completo.",
                fotoResId = R.drawable.foto_post_detalle_rin,
                likes = 197
            )
        )

        binding.rvPublicaciones.layoutManager = LinearLayoutManager(this)
        binding.rvPublicaciones.adapter = PublicacionAdapter(publicaciones)
    }

    private fun configurarBottomNav() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.nav_inicio) {
                true
            } else {
                Toast.makeText(this, R.string.toast_proximamente, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}
