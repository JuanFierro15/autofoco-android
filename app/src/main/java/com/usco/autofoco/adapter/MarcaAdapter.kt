package com.usco.autofoco.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usco.autofoco.databinding.ItemMarcaBinding
import com.usco.autofoco.db.MarcaEntity

class MarcaAdapter(private var marcas: List<MarcaEntity>) :
    RecyclerView.Adapter<MarcaAdapter.MarcaViewHolder>() {

    class MarcaViewHolder(val binding: ItemMarcaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val binding = ItemMarcaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarcaViewHolder(binding)
    }

    override fun getItemCount(): Int = marcas.size

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        val marca = marcas[position]
        holder.binding.tvNombreMarca.text = marca.makeName
    }

    fun actualizar(nuevasMarcas: List<MarcaEntity>) {
        marcas = nuevasMarcas
        notifyDataSetChanged()
    }
}
