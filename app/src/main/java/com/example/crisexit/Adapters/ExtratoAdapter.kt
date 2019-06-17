package com.example.crisexit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.crisexit.BottomMenuActivity
import com.example.crisexit.GuiaFragment
import com.example.crisexit.Holders.ExtratoHolder
import com.example.crisexit.Holders.GuiaHolder
import com.example.crisexit.Model.ExtratoItem
import com.example.crisexit.R

class ExtratoAdapter(private val extratoList: List<ExtratoItem>): Adapter<ExtratoHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtratoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guia_item, parent, false)

        return ExtratoHolder(view)
    }

    override fun getItemCount(): Int {
        return extratoList.size
    }

    override fun onBindViewHolder(holder: ExtratoHolder, position: Int) {
        val extratoItem = extratoList[position]

        holder?.let{
            it.bindView(extratoItem)
            //holder.image.text = guiaItem.guiaImage falta colocar o picasso para adicionar imagem
        }

    }

}