package com.example.crisexit.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.crisexit.Holders.ExtractHolder
import com.example.crisexit.Model.ExtractItem
import com.example.crisexit.R

class ExtractAdapter(private val extractList: List<ExtractItem>): Adapter<ExtractHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtractHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.extract_item, parent, false)

        return ExtractHolder(view)
    }

    override fun getItemCount(): Int {
        return extractList.size
    }

    override fun onBindViewHolder(holder: ExtractHolder, position: Int) {
        val extratoItem = extractList[position]

        holder?.let{
            it.bindView(extratoItem)
            //holder.image.text = guiaItem.guiaImage falta colocar o picasso para adicionar imagem
        }

    }

}