package com.example.crisexit

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.crisexit.Adapters.ExtractAdapter
import com.example.crisexit.Adapters.GuiaAdapter
import com.example.crisexit.Model.ExtractItem
import com.example.crisexit.Model.GuiaItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.opencensus.resource.Resource
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.w3c.dom.Text

class FireBaseHandler {
    private var dataBase = FirebaseFirestore.getInstance()
    private var valorTotal: Double = 0.0

    fun getGuiaItens(recyclerView: RecyclerView, activity: FragmentActivity?) {

        dataBase.collection("guiaItens")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    var guiaItens = mutableListOf<GuiaItem>()

                    for (document in task.result!!) {
                        var imageUrl: String = document.data.get("image").toString()
                        var title: String = document.data.get("title").toString()

                        guiaItens.add(GuiaItem(imageUrl, title))

                        Log.d(TAG, document.id + " => " + document.data.get("image"))
                    }

                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = GuiaAdapter(guiaItens)

                }
                else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            })
    }

    fun getExtratoItens(recyclerView: RecyclerView, activity: FragmentActivity?, valorSaldo: TextView, swipeRefreshLayout: SwipeRefreshLayout){
        dataBase.collection("Transactions")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if(task.isSuccessful) {
                    var extractItens = mutableListOf<ExtractItem>()
                    var sum: Double = 0.0
                    for(document in task.result!!) {
                        var descricao: String = document.data.get("descricao").toString()
                        var valor = document.data.get("valor").toString()

                        sum += valor.toDouble()

                        extractItens.add(ExtractItem(descricao, valor))
                    }


                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = ExtractAdapter(extractItens)
                    //swipeRefreshLayout.isRefreshing=false

                    if(sum < 0){
                        valorSaldo.setTextColor("#FF1744".toColorInt())
                    }
                    valorSaldo.text = toCashFormat(sum)
                }
                else {
                    Log.d(TAG, "Error Getting Extrato Documents", task.exception)
                }
            })
    }

    fun setextractItens(described: String, valor: String){
        val item = HashMap<String, String>()
        item.put("descricao", described)
        item.put("valor", valor)
        dataBase.collection("Transactions")
            .add(item)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.id)
            })
            .addOnFailureListener(OnFailureListener {
                    e -> Log.w(TAG, "Error adding document", e)
            })
    }

    fun calculateValorTotal(extractItens: MutableList<ExtractItem>): Double{
        var sum: Double = 0.0
        for(valor in extractItens){
            sum += valor.valor.toDouble()
        }

        return sum
    }

    fun toCashFormat(valor: Double): String {

        return ("R$ ".plus(String.format("%.2f", valor)))
    }

}