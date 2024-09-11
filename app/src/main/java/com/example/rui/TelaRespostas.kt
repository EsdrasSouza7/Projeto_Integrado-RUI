package com.example.rui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityTelaRespostasBinding
import java.util.zip.Inflater

class TelaRespostas : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var binding: ActivityTelaRespostasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaRespostasBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)
        dbHelper = DBHelper(this)

        updateUI()

        binding.btVoltar.setOnClickListener{
            finish()
        }
    }

    private fun updateUI() {
        val respostas = dbHelper.getAllData()
        val resultado = StringBuilder()
        for (resposta in respostas) {
            resultado.append("Data: ${resposta.data} \nEmail: ${resposta.email}\nCarne Escolhida: ${resposta.qProteina}\nAvaliações:\nProteina: ${resposta.rProteina}\nAcompanhamento: ${resposta.acompanhamento}\nBebida: ${resposta.bebida}\nSugestão: ${resposta.sugestao}\n\n")
        }
        val textViewResultado = findViewById<TextView>(R.id.textViewRespostas)
        textViewResultado.text = resultado.toString()
    }

}