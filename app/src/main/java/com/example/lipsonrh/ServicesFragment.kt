package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class ServicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_services,
            container,
            false
        )

        /*
         * ABRIR HOLERITES
         */

        val btnHolerites =
            view.findViewById<LinearLayout>(
                R.id.btnHolerites
            )

        btnHolerites.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    HoleriteFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        /*
         * ABRIR GERAÇÃO DE HOLERITE
         */

        val btnGerarHolerite =
            view.findViewById<LinearLayout>(
                R.id.btnGerarHolerite
            )

        btnGerarHolerite.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    GerarHoleriteFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
