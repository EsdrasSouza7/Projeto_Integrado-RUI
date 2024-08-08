package com.example.rui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityTelaFormularioBinding
import java.text.SimpleDateFormat
import java.util.Date

class TelaFormulario : AppCompatActivity() {

    private lateinit var binding: ActivityTelaFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaFormularioBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.DataAtual.text = getData()
        binding.CarneVermelha.setOnClickListener {
            Ativar()
        }
        binding.CarneBranca.setOnClickListener {
            Ativar()
        }
        binding.CarneVegetariana.setOnClickListener {
            Ativar()
        }
    }
    private fun Ativar(){
        val caixa = binding.caixaDePergunta
        caixa.visibility = View.VISIBLE
    }
    private fun getData(): String{
        val formataData = SimpleDateFormat("dd/MM/yy")
        val data = Date()
        return formataData.format(data)
    }
}