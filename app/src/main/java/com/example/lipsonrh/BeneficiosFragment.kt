package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class BeneficiosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beneficios, container, false)

        // Lista de dados baseada na imagem que você enviou
        configurarCard(view.findViewById(R.id.cardMedico), "Convênio Médico", "Plano de saúde para você e seus dependentes.", R.drawable.ic_medico)
        configurarCard(view.findViewById(R.id.cardOdonto), "Convênio Odontológico", "Plano odontológico para você e seus dependentes.", R.drawable.ic_dente)
        configurarCard(view.findViewById(R.id.cardTransporte), "Vale Transporte", "Benefício para deslocamento casa-trabalho.", R.drawable.ic_bus)
        configurarCard(view.findViewById(R.id.cardCreche), "Auxílio Creche", "Apoio financeiro para educação do seu filho.", R.drawable.ic_creche)

        // Exemplo para o Pendente
        configurarCard(view.findViewById(R.id.cardAlimentacao), "Vale Alimentação", "Crédito mensal para suas compras.", R.drawable.ic_cesta, "Pendente", "#FFA500")

        // Exemplo para o Inativo
        configurarCard(view.findViewById(R.id.cardWellhub), "Wellhub (Gympass)", "Bem-estar e qualidade de vida para você.", R.drawable.ic_academia, "Inativo", "#9E9E9E")

        return view
    }

    private fun configurarCard(
        card: View,
        titulo: String,
        subtitulo: String,
        iconeRes: Int,
        status: String = "Ativo",
        corStatus: String = "#4CAF50"
    ) {
        card.findViewById<TextView>(R.id.txtTituloBeneficio).text = titulo
        card.findViewById<TextView>(R.id.txtSubtituloBeneficio).text = subtitulo
        card.findViewById<ImageView>(R.id.imgBeneficioIcone).setImageResource(iconeRes)

        val txtStatus = card.findViewById<TextView>(R.id.txtStatus)
        txtStatus.text = status
        txtStatus.setTextColor(android.graphics.Color.parseColor(corStatus))
    }
}