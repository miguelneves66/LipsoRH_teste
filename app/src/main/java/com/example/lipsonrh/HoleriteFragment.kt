package com.example.lipsonrh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File

class HoleriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_holerite,
            container,
            false
        )

        val containerHolerites =
            view.findViewById<LinearLayout>(R.id.containerHolerites)

        val pasta = File(
            requireContext().getExternalFilesDir(null),
            "holerites"
        )

        if (pasta.exists()) {

            val arquivos = pasta.listFiles()

            arquivos?.forEach { arquivo ->

                val item = LayoutInflater.from(requireContext())
                    .inflate(
                        R.layout.item_holerite,
                        containerHolerites,
                        false
                    )

                val txtNome =
                    item.findViewById<TextView>(R.id.txtNomeArquivo)

                txtNome.text = arquivo.name

                item.setOnClickListener {

                    val uri: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "${requireContext().packageName}.provider",
                        arquivo
                    )

                    val intent = Intent(Intent.ACTION_VIEW)

                    intent.setDataAndType(uri, "application/pdf")

                    intent.flags =
                        Intent.FLAG_GRANT_READ_URI_PERMISSION

                    startActivity(intent)
                }

                containerHolerites.addView(item)
            }
        }

        return view
    }
}
