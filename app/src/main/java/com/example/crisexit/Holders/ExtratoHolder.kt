package com.example.crisexit.Holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Model.ExtratoItem
import com.example.crisexit.Model.GuiaItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.extract_item.view.*
import kotlinx.android.synthetic.main.guia_item.view.*

class ExtratoHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bindView(extratoItem: ExtratoItem){
        val description = itemView.descricao
        val valor = itemView.valorItem

        description.text = extratoItem.descricao
        valor.text = extratoItem.valor.toString()
    }


}