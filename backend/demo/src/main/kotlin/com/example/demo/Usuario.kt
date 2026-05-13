package com.example.demo

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "usuario")
class Usuario(
    @Id
    val cpf: String = "",
    val nome: String = "",
    val endereco: String = "",
    val dataAdmissao: String = "", // Garanta que o nome aqui...
    val cargo: String = "",
    val senha: String = ""
)