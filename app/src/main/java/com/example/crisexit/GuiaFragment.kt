package com.example.crisexit


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Adapters.GuiaAdapter
import com.example.crisexit.Model.GuiaItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_guia.*


class GuiaFragment : Fragment() {

    private var dataBase = FirebaseFirestore.getInstance()

    companion object {
        fun newInstance(): GuiaFragment {
            var guiaFragment = GuiaFragment()
            var args = Bundle()
            guiaFragment.arguments = args

            return guiaFragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guia, container, false)

        val recyclerView = view.findViewById(R.id.guia_list_recyclerview) as RecyclerView
        loadGuiaItens(recyclerView)

//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.adapter = GuiaAdapter(guiaItens())


        return view
    }

// pensar em tirar ou não
//    private fun guiaItens(): List<GuiaItem> {
//        return
////       return listOf(
////           GuiaItem("Teste01",
////               "Esperando picasso"),
////           GuiaItem("Teste02",
////                   "Esperando picasso 02"))
//    }



    private fun loadGuiaItens(recyclerView: RecyclerView) {

        dataBase.collection("guiaItens")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    val guiaItens = mutableListOf<GuiaItem>()
                    for (document in task.result!!) {
                        var imageUrl: String = document.data.get("image").toString()
                        var title: String = document.data.get("title").toString()
//                        guiaItem.let {
//                            it.guiaImage = document.data.get("image").toString()
//                            it.guiaTitle = document.data.get("title").toString()
//                        }

                        guiaItens.add(GuiaItem(imageUrl,title))

                        Log.d(TAG, document.id + " => " + document.data.get("image"))
                    }

                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = GuiaAdapter(guiaItens)

                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            })


//        dataBase!!.collection("guiaItens")
//            .get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val guiaItens = mutableListOf<GuiaItem>()
//
//                    for (doc in task.result!!) {
//                        val note = doc.toObject(GuiaItem::class.java)
//
//                        guiaItens.add(note)
//                    }
//                    recyclerView.layoutManager = LinearLayoutManager(activity)
//                    recyclerView.adapter = GuiaAdapter(guiaItens)
//
//
////                    mAdapter = NoteRecyclerViewAdapter(notesList, applicationContext, firestoreDB!!)
////                    val mLayoutManager = LinearLayoutManager(applicationContext)
////                    rvNoteList.layoutManager = mLayoutManager
////                    rvNoteList.itemAnimator = DefaultItemAnimator()
////                    rvNoteList.adapter = mAdapter
//
//                } else {
//                    Log.d("FIREBASE", "Error getting documents: ", task.exception)
//                }
//
//            }
    }



}
