package com.example.crisexit.Holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Model.GuiaItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.guia_item.view.*

class GuiaHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bindView(guiaItem: GuiaItem){
        val title = itemView.guia_item_title
        val image = itemView.guia_item_image

        title.text = guiaItem.guiaTitle
        Picasso.get().load(guiaItem.guiaImage).into(image)
    }


}