package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/auth")
class AuthController(
    val usuarioRepository: UsuarioRepository,
    val rhRepository: RHRepository
) {

    @PostMapping("/login")
    fun login(@RequestBody credenciais: Map<String, String>): ResponseEntity<Map<String, String>> {
        val cpf = credenciais["cpf"] ?: ""
        val senha = credenciais["senha"] ?: ""

        // 1. Tenta buscar na tabela de Usuários
        val usuario = usuarioRepository.findById(cpf)
        if (usuario.isPresent && usuario.get().senha == senha) {
            return ResponseEntity.ok(mapOf(
                "tipo" to "USUARIO",
                "nome" to usuario.get().nome
            ))
        }

        // 2. Se não achou, tenta na tabela de RH
        val rh = rhRepository.findById(cpf)
        if (rh.isPresent && rh.get().senha == senha) {
            return ResponseEntity.ok(mapOf(
                "tipo" to "RH",
                "nome" to rh.get().nome
            ))
        }

        // 3. Se não achou em nenhum dos dois
        return ResponseEntity.status(401).body(mapOf("erro" to "CPF ou senha incorretos"))
    }
}