package com.example.lipsonrh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var nomeUsuario: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Recebe o nome enviado pela LoginActivity após o sucesso
        nomeUsuario = intent.getStringExtra("NOME_USUARIO")

        // Inicia o app diretamente no HomeFragment
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_servicos -> loadFragment(ServicesFragment())
                R.id.nav_calendario -> loadFragment(CalendarioFragment())
                R.id.nav_perfil -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        if (fragment is HomeFragment && nomeUsuario != null) {
            val bundle = Bundle()
            bundle.putString("USER_NAME", nomeUsuario) // Garante que a chave seja USER_NAME
            fragment.arguments = bundle
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}