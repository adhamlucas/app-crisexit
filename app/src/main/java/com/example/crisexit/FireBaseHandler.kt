package com.example.crisexit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Adapters.GuiaAdapter
import com.example.crisexit.Model.GuiaItem
import com.google.android.gms.tasks.OnCompleteListener
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

}