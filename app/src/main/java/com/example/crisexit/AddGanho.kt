package com.example.crisexit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.crisexit.Adapters.CategoryGanhoAdapter
import com.example.crisexit.Model.CategoryItem
import kotlinx.android.synthetic.main.activity_add_ganho.*

class AddGanho : AppCompatActivity() {
    var fireBaseHandler = FireBaseHandler()
    var selectedCategory: Int = -1
    lateinit var categories: MutableList<CategoryItem>

    override fun onResume() {
        super.onResume()

        loadCategories()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ganho)

        var valorGanho: TextView = findViewById(R.id.valorGanho)
        var descricaoGanho: EditText = findViewById(R.id.descricaoGanho)
        var buttonAddGanho: Button = findViewById(R.id.button_adicionar_ganho)
        val spinnerCategory: Spinner = findViewById(R.id.spinner_category_add_ganho)

        loadCategories()

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCategory = categories[position].id
                Log.d("FUNCIONOU", "Nome Categories: ${categories[position].name}")
            }
        }

        var valor = 0.0
        valorGanho.text = toCashFormat(valor)

        var descricao: String = descricaoGanho.text.toString()
        Log.d(TAG, descricao)

        buttonAddGanho.setOnClickListener{
            fireBaseHandler.setextractItens(descricao, valor.toString())
        }


    }

    fun toCashFormat(valor: Double): String {

        return ("R$ ".plus(String.format("%.2f", valor)))
    }

    //FALTA SELECIONAR SOMENTE AS CATEGORIA DE ENTRADA!!!!!
    fun loadCategories(){
        categories = mutableListOf()

        categories.add(CategoryItem(id = 1,
                                name = "Investimentos",
                                icon = R.drawable.entrada_investimento,
                                tipo = "Entrada"))

        categories.add(CategoryItem(id = 2,
            name = "Presentes",
            icon = R.drawable.entrada_presente,
            tipo = "Entrada"))

        categories.add(CategoryItem(id = 3,
            name = "remuneracao",
            icon = R.drawable.entrada_remuneracao,
            tipo = "Entrada"))

        spinner_category_add_ganho.adapter = CategoryGanhoAdapter(this, categories)

    }
}
