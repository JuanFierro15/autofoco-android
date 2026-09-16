package com.usco.autofoco.adapter

import android.content.res.ColorStateList
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.usco.autofoco.R
import com.usco.autofoco.databinding.ItemPublicacionBinding
import com.usco.autofoco.model.Publicacion

class PublicacionAdapter(private val publicaciones: List<Publicacion>) :
    RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder>() {

    class PublicacionViewHolder(val binding: ItemPublicacionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val binding = ItemPublicacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PublicacionViewHolder(binding)
    }

    override fun getItemCount(): Int = publicaciones.size

    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val publicacion = publicaciones[position]
        val binding = holder.binding

        binding.tvUsuarioPost.text = publicacion.usuario
        binding.tvVehiculoPost.text = publicacion.vehiculo
        binding.ivFoto.setImageResource(publicacion.fotoResId)
        binding.tvMarca.text = publicacion.marca
        binding.tvAnio.text = publicacion.anio
        binding.tvModificacion.text = publicacion.modificacion

        val textoCaption = "${publicacion.usuario} ${publicacion.caption}"
        val captionConEstilo = SpannableString(textoCaption)
        captionConEstilo.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            publicacion.usuario.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvCaption.text = captionConEstilo

        actualizarLike(binding, publicacion)

        binding.ivLike.setOnClickListener {
            publicacion.meGusta = !publicacion.meGusta
            publicacion.likes += if (publicacion.meGusta) 1 else -1
            actualizarLike(binding, publicacion)
        }
    }

    private fun actualizarLike(binding: ItemPublicacionBinding, publicacion: Publicacion) {
        val contexto = binding.root.context

        if (publicacion.meGusta) {
            binding.ivLike.setImageResource(R.drawable.ic_corazon_lleno)
            binding.ivLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(contexto, R.color.color_acento))
        } else {
            binding.ivLike.setImageResource(R.drawable.ic_corazon_borde)
            binding.ivLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(contexto, R.color.color_texto_secundario))
        }

        binding.tvLikes.text = contexto.getString(R.string.formato_likes, publicacion.likes)
    }
}
