package com.example.lipsonrh

import com.google.gson.annotations.SerializedName

data class Usuario(
    val cpf: String,
    val nome: String,
    val endereco: String,
    val dataAdmissao: String, // ...seja IGUAL ao nome aqui (sem underline)
    val cargo: String,
    val senha: String
)