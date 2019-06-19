package com.example.crisexit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.crisexit.Model.CategoryItem
import com.example.crisexit.R
import kotlinx.android.synthetic.main.category_spinner.view.*

class CategoryGanhoAdapter(ctx: Context, categoryItens: List<CategoryItem>): ArrayAdapter<CategoryItem>(ctx, 0, categoryItens) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val category= getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.category_spinner,
            parent,
            false
        )

        view.category_icon.setImageResource(category.icon)
        view.category_name.text = category.name
        return view
    }
}