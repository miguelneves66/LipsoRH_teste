package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class GerarHoleriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_gerar_holerite,
            container,
            false
        )

        val edtNome =
            view.findViewById<EditText>(R.id.edtNome)

        val edtCargo =
            view.findViewById<EditText>(R.id.edtCargo)

        val edtSalario =
            view.findViewById<EditText>(R.id.edtSalario)

        val edtDescontos =
            view.findViewById<EditText>(R.id.edtDescontos)

        val edtMes =
            view.findViewById<EditText>(R.id.edtMes)

        val btnGerar =
            view.findViewById<Button>(R.id.btnGerarPdf)

        btnGerar.setOnClickListener {

            val holerite = Holerite(
                nomeFuncionario = edtNome.text.toString(),
                cargo = edtCargo.text.toString(),
                salario = edtSalario.text.toString()
                    .toDoubleOrNull() ?: 0.0,
                descontos = edtDescontos.text.toString()
                    .toDoubleOrNull() ?: 0.0,
                mesReferencia = edtMes.text.toString(),
                caminhoPdf = ""
            )

            val pdfHelper = PdfHelper()

            val arquivo = pdfHelper.gerarPdf(
                requireContext(),
                holerite
            )

            Toast.makeText(
                requireContext(),
                "PDF criado com sucesso!",
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }
}
