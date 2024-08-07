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
    }
    private fun cardapio(){

        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtEntrar.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({navegateTelaCardapio()},500)
    }
    private fun navegateTelaCardapio(){
        val intent = Intent(this, TelaCardapio::class.java)
        startActivity(intent)
        finish()
    }
    private fun avaliar(){

        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.BtAvaliar.isEnabled = false
        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({navegateTelaFormulario()},500)
    }
    private fun navegateTelaFormulario(){
        val intent = Intent(this, TelaFormulario::class.java)
        startActivity(intent)
        finish()
    }
}