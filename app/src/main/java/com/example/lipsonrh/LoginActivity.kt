package com.example.lipsonrh

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCpf = findViewById<EditText>(R.id.etCpf)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            // O uso do .trim() é essencial para evitar espaços invisíveis do teclado
            val cpf = etCpf.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (cpf.isNotEmpty() && senha.isNotEmpty()) {
                efetuarLogin(cpf, senha)
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun efetuarLogin(cpfDigitado: String, senhaDigitada: String) {
        val service = RetrofitClient.getRetrofitInstance().create(RetrofitService::class.java)

        service.buscarUsuarioPorCpf(cpfDigitado).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()

                    // Log que confirmou a conexão com o Spring Boot e MySQL
                    Log.d("SUCESSO_API", "Usuário encontrado: ${usuario?.nome}")

                    // Verificação da senha localmente após receber o objeto do banco
                    if (usuario != null && usuario.senha == senhaDigitada) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        // Passando o nome para a próxima tela
                        intent.putExtra("NOME_USUARIO", usuario.nome)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Senha incorreta!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ERRO_API", "Código: ${response.code()}")
                    Toast.makeText(baseContext, "Usuário não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                // Caso o servidor esteja desligado ou o IP mude, o erro aparecerá aqui
                Log.e("FALHA_CONEXAO", "Mensagem: ${t.message}")
                Toast.makeText(baseContext, "Erro de rede: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}