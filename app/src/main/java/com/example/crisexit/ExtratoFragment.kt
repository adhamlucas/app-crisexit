package com.example.crisexit


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.getbase.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import org.w3c.dom.Text

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
        val valorTotal: TextView = view.findViewById(R.id.valorSaldo)
        val swipeRefrashLayout: SwipeRefreshLayout = view.findViewById(R.id.extracted_list_refresh)

        loadExtratoItens(recyclerView, valorTotal, swipeRefrashLayout)


        val addGanhoButton: FloatingActionButton  = view.findViewById(R.id.button_add_ganho)
        val addGastoButton: FloatingActionButton = view.findViewById(R.id.button_add_gasto)

        swipeRefrashLayout.setOnRefreshListener {
            loadExtratoItens(recyclerView, valorTotal, swipeRefrashLayout)

            swipeRefrashLayout.isRefreshing=false
        }
        swipeRefrashLayout.isRefreshing = false

        addGanhoButton.setOnClickListener {
            val intent = Intent(context, AddGanho::class.java)
            startActivity(intent)
        }
        addGastoButton.setOnClickListener{
            val intent = Intent(context, AddGasto::class.java)
            startActivity(intent)
        }
    }

    fun loadExtratoItens(recyclerView: RecyclerView, valorTotal: TextView, swipeRefreshLayout: SwipeRefreshLayout){
        fireBaseData.getExtratoItens(recyclerView, activity, valorTotal, swipeRefreshLayout)

    }

}
