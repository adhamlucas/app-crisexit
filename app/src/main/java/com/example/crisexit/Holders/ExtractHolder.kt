package com.example.crisexit.Holders

import android.view.View
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Model.ExtractItem
import kotlinx.android.synthetic.main.extract_item.view.*

class ExtractHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bindView(extratoItem: ExtractItem){
        val description = itemView.descricao
        val valor = itemView.valorItem

        description?.text = extratoItem.descricao;
        setColor(valor, extratoItem.valor)
        valor.text = toCashFormat(extratoItem.valor.toDouble())
    }

    fun toCashFormat(valor: Double): String {

        return ("R$ ".plus(String.format("%.2f", valor)))
    }

    fun setColor(valor: TextView, valorItem: String){
        if(valorItem.toDouble() > 0){

            valor.setTextColor("#76FF03".toColorInt())
        }
    }


}