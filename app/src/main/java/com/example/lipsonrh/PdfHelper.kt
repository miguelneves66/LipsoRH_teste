package com.example.lipsonrh

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class PdfHelper {

    fun gerarPdf(
        context: Context,
        holerite: Holerite
    ): File {

        val document = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()

        paint.textSize = 22f
        paint.isFakeBoldText = true

        canvas.drawText("LIPSON RH", 220f, 80f, paint)

        paint.textSize = 16f
        paint.isFakeBoldText = false

        canvas.drawText("Funcionário: ${holerite.nomeFuncionario}", 60f, 180f, paint)
        canvas.drawText("Cargo: ${holerite.cargo}", 60f, 220f, paint)
        canvas.drawText("Mês: ${holerite.mesReferencia}", 60f, 260f, paint)
        canvas.drawText("Salário: R$ ${holerite.salario}", 60f, 320f, paint)
        canvas.drawText("Descontos: R$ ${holerite.descontos}", 60f, 360f, paint)

        val liquido = holerite.salario - holerite.descontos

        paint.isFakeBoldText = true
        canvas.drawText("Líquido: R$ $liquido", 60f, 430f, paint)

        document.finishPage(page)

        val pasta = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "holerites"
        )

        if (!pasta.exists()) {
            pasta.mkdirs()
        }

        val arquivo = File(
            pasta,
            "holerite_${holerite.nomeFuncionario}_${holerite.mesReferencia}.pdf"
        )

        document.writeTo(FileOutputStream(arquivo))
        document.close()

        return arquivo
    }
}
