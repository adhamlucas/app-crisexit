package com.example.crisexit


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExtratoFragment : Fragment() {
    private var fireBaseData = FireBaseHandler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extrato, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById(R.id.extrato_list_recycler_view) as RecyclerView
        loadExtratoItens(recyclerView)
    }

    fun loadExtratoItens(recyclerView: RecyclerView){
        fireBaseData.getExtratoItens(recyclerView, activity)
    }

}
