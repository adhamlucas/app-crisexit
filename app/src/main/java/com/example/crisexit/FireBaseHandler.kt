package com.example.crisexit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Adapters.ExtratoAdapter
import com.example.crisexit.Adapters.GuiaAdapter
import com.example.crisexit.Model.ExtratoItem
import com.example.crisexit.Model.GuiaItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FireBaseHandler {
    private var dataBase = FirebaseFirestore.getInstance()

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

    fun getExtratoItens(recyclerView: RecyclerView, activity: FragmentActivity?){
        dataBase.collection("Transactions")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if(task.isSuccessful) {
                    var extratoItens = mutableListOf<ExtratoItem>()

                    for(document in task.result!!) {
                        var descricao: String = document.data.get("descricao").toString()
                        var valor: Double = document.data.get("valor") as Double

                        extratoItens.add(ExtratoItem(descricao, valor))
                    }

                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = ExtratoAdapter(extratoItens)
                }
                else {
                    Log.d(TAG, "Error Getting Extrato Documents", task.exception)
                }
            })
    }

    fun setExtratoItens(descricao: String, valor: Double){
        dataBase.collection("Transaction")
            .add(ExtratoItem(descricao, valor))
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.id)
            })
            .addOnFailureListener(OnFailureListener {
                    e -> Log.w(TAG, "Error adding document", e)
            })
    }

}