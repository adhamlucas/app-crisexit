package com.example.crisexit


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crisexit.Adapters.GuiaAdapter
import com.example.crisexit.Model.GuiaItem
import com.google.firebase.firestore.FirebaseFirestore


class GuiaFragment : Fragment() {

    private var dataBase = FirebaseFirestore.getInstance()
    private var FireBaseData = FireBaseHandler()

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

        return view
    }

    private fun loadGuiaItens(recyclerView: RecyclerView) {
        FireBaseData.getGuiaItens(recyclerView, activity)
    }



}
