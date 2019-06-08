package com.example.crisexit


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class GuiaFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_guia, container, false)
    }


}
