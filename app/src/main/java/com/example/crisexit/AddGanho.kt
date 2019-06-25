package com.example.crisexit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.crisexit.Adapters.CategoryGanhoAdapter
import com.example.crisexit.Model.CategoryItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_ganho.*
import org.json.JSONObject
import java.lang.Exception

class AddGanho : AppCompatActivity() {
    var fireBaseHandler = FireBaseHandler()
    var selectedCategory: Int = -1
    lateinit var categories: MutableList<CategoryItem>

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ganho)

        val intent = getIntent()
        val token = intent.getStringExtra("TOKEN")

        var valorGanho: TextInputEditText = findViewById(R.id.VALOR)
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

        valorGanho.setOnClickListener {
            valorGanho.hint = toCashFormat(valor)
        }



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
