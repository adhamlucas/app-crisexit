package com.example.crisexit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import org.jetbrains.anko.toast

class AddGanho : AppCompatActivity() {
    var fireBaseHandler = FireBaseHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ganho)

        var valorGanho: TextView = findViewById(R.id.valorGanho)
        var descricaoGanho: EditText = findViewById(R.id.descricaoGanho)
        var buttonGanho: Button = findViewById(R.id.button_adicionar_ganho)
        var valor = 0.0
        valorGanho.text = toCashFormat(valor)

        var descricao: String = descricaoGanho.text.toString()
        Log.d(TAG, descricao)
        buttonGanho.setOnClickListener{
            fireBaseHandler.setextractItens(descricao, valor.toString())
        }


    }

    fun toCashFormat(valor: Double): String {

        return ("R$ ".plus(String.format("%.2f", valor)))
    }
}
