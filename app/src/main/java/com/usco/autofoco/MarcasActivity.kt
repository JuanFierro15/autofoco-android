package com.usco.autofoco

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.usco.autofoco.adapter.MarcaAdapter
import com.usco.autofoco.api.RetrofitClient
import com.usco.autofoco.databinding.ActivityMarcasBinding
import com.usco.autofoco.db.AutofocoDatabase
import com.usco.autofoco.db.MarcaEntity
import kotlinx.coroutines.launch

class MarcasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarcasBinding
    private val adapter = MarcaAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarcasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMarcas.layoutManager = LinearLayoutManager(this)
        binding.rvMarcas.adapter = adapter

        cargarMarcas()
    }

    private fun cargarMarcas() {
        lifecycleScope.launch {
            val dao = AutofocoDatabase.obtenerInstancia(this@MarcasActivity).marcaDao()
            try {
                val respuesta = RetrofitClient.api.getMakesForVehicleType("car")
                val entidades = respuesta.Results.map { MarcaEntity(it.MakeId, it.MakeName) }
                dao.insertarTodas(entidades)
                adapter.actualizar(entidades)
            } catch (e: Exception) {
                val guardadas = dao.obtenerTodas()
                adapter.actualizar(guardadas)
            }
            binding.pbCargaMarcas.visibility = View.GONE
        }
    }
}
