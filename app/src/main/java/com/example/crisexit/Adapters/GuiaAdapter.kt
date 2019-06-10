package com.example.crisexit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.crisexit.BottomMenuActivity
import com.example.crisexit.GuiaFragment
import com.example.crisexit.Holders.GuiaHolder
import com.example.crisexit.Model.GuiaItem
import com.example.crisexit.R

class GuiaAdapter(private val guiaItens: List<GuiaItem>): Adapter<GuiaHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuiaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guia_item, parent, false)

        return GuiaHolder(view)
    }

    override fun getItemCount(): Int {
        return guiaItens.size
    }

    override fun onBindViewHolder(holder: GuiaHolder, position: Int) {
        val guiaItem = guiaItens[position]

        holder?.let{
            it.bindView(guiaItem)
            //holder.image.text = guiaItem.guiaImage falta colocar o picasso para adicionar imagem
        }

    }

}