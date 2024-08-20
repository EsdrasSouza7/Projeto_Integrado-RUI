package com.example.rui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.BtAvaliar.setOnClickListener{
            avaliar()
        }

        binding.BtEntrar.setOnClickListener{
            cardapio()
        }
        binding.BtnRespostas.setOnClickListener{
            respostas()
        }
    }
    private fun cardapio(){
        binding.progressBar.visibility = View.VISIBLE

        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtEntrar.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({ativarButoes()},500)
        Handler(Looper.getMainLooper()).postDelayed({navegateTelaCardapio()},500)
    }

    private fun navegateTelaCardapio(){
        val intent = Intent(this, TelaCardapio::class.java)
        startActivity(intent)
    }
    private fun avaliar(){
        binding.progressBar.visibility = View.VISIBLE

        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtAvaliar.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({ativarButoes()},500)
        Handler(Looper.getMainLooper()).postDelayed({navegateTelaFormulario()},500)

    }
    private fun navegateTelaFormulario(){
        val intent = Intent(this, TelaFormulario::class.java)
        startActivity(intent)
    }
    private fun respostas(){
        binding.progressBar.visibility = View.VISIBLE

        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtnRespostas.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({ativarButoes()},500)
        Handler(Looper.getMainLooper()).postDelayed({navegateTelaRespostas()},500)
    }

    private fun navegateTelaRespostas() {
        val intent = Intent(this, TelaRespostas::class.java)
        startActivity(intent)
    }

    private fun ativarButoes() {
        binding.progressBar.visibility = View.GONE
        binding.BtAvaliar.isEnabled = true
        binding.BtEntrar.isEnabled = true
        binding.BtnRespostas.isEnabled = true
        binding.BtAvaliar.setTextColor(Color.BLACK)
        binding.BtEntrar.setTextColor(Color.BLACK)
        binding.BtnRespostas.setTextColor(Color.BLACK)
    }
}