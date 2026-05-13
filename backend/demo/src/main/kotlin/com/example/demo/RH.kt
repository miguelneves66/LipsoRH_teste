package com.example.demo

import jakarta.persistence.*

@Entity
@Table(name = "rh")
class RH(
    @Id
    val cpf: String = "",
    val nome: String = "",
    val endereco: String = "",
    val dataAdmissao: String = "",
    val cargo: Int = 0, // Na sua estrutura original do BD, o cargo no RH era INT
    val senha: String = ""
)