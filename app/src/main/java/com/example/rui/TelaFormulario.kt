package com.example.rui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        var butaoApertado = "None"


        binding.DataAtual.text = getData()
        binding.CarneVermelha.setOnClickListener {
            ativar()
            butaoApertado = "Carne Vermelha"
            binding.CarneVermelha.setBackgroundResource(R.color.Blue_Dark)
            binding.CarneBranca.setBackgroundResource(R.color.Primaria)
            binding.CarneVegetariana.setBackgroundResource(R.color.Primaria)
        }
        binding.CarneBranca.setOnClickListener {
            ativar()
            butaoApertado = "Carne Branca"
            binding.CarneBranca.setBackgroundResource(R.color.Blue_Dark)
            binding.CarneVermelha.setBackgroundResource(R.color.Primaria)
            binding.CarneVegetariana.setBackgroundResource(R.color.Primaria)
        }
        binding.CarneVegetariana.setOnClickListener {
            ativar()
            butaoApertado = "Vegetariana"
            binding.CarneVegetariana.setBackgroundResource(R.color.Blue_Dark)
            binding.CarneBranca.setBackgroundResource(R.color.Primaria)
            binding.CarneVermelha.setBackgroundResource(R.color.Primaria)
        }

        binding.ImagemCarneRuim.setOnClickListener{
            binding.CarneRuim.isChecked = true
        }
        binding.ImagemCarneNeutra.setOnClickListener{
            binding.CarneNeutra.isChecked = true
        }
        binding.ImagemCarneBoa.setOnClickListener{
            binding.CarneBoa.isChecked = true
        }
        binding.ImagemAcompanhamentoRuim.setOnClickListener{
            binding.AcompanhamentoRuim.isChecked = true
        }
        binding.ImagemAcompanhamentoNeutro.setOnClickListener{
            binding.AcompanhamentoNeutro.isChecked = true
        }
        binding.ImagemAcompanhamentoBom.setOnClickListener{
            binding.AcompanhamentoBom.isChecked = true
        }
        binding.ImagemBebidaRuim.setOnClickListener{
            binding.BebidaRuim.isChecked = true
        }
        binding.ImagemBebidaNeutra.setOnClickListener{
            binding.BebidaNeutra.isChecked = true
        }
        binding.ImagemBebidaBom.setOnClickListener{
            binding.BebidaBoa.isChecked = true
        }
        binding.btEnviar.setOnClickListener{
            val confirmEnvio = AlertDialog.Builder(this)
            confirmEnvio.setMessage("Confirmar Envio?")
            confirmEnvio.setNeutralButton("Cancelar", null)
            confirmEnvio.setPositiveButton("positivo") { _: DialogInterface?, _: Int -> enviar() }
            confirmEnvio.show()
        }
    }
    private fun ativar(){
        val caixa = binding.caixaDePergunta
        caixa.visibility = View.VISIBLE
    }
    private fun getData(): String{
        val formataData = SimpleDateFormat("dd/MM/yy")
        val data = Date()
        return formataData.format(data)
    }
    private fun navegateTelaConfirm(){
        val intent = Intent(this, TelaConfirm::class.java)
        startActivity(intent)
        finish()
    }
    private fun enviar(){
        val OpcaoProteina = findViewById<RadioGroup>(R.id.AvaliacaoCarne)
        val OpcaoAcompanhamento = findViewById<RadioGroup>(R.id.AvaliacaoAcompanhamento)
        val OpcaoBebida = findViewById<RadioGroup>(R.id.Avaliacaobebida)

        val selectProteina = OpcaoProteina.checkedRadioButtonId
        val proteina =findViewById<RadioButton>(selectProteina)
        if (selectProteina == -1){
            Toast.makeText(this, "Avaliação da Proteina não Selecionado", Toast.LENGTH_SHORT).show()
            //Mensagem de Erro. ver se ja ta bom
        }else{
            //Programar a Confirmação do Dado
        }

        val selectAcompanhamento = OpcaoAcompanhamento.checkedRadioButtonId
        val acompanhamento =findViewById<RadioButton>(selectAcompanhamento)
        if (selectAcompanhamento == -1){
            Toast.makeText(this, "Avaliação da Proteina não Selecionado", Toast.LENGTH_SHORT).show()
            //Mensagem de Erro. ver se ja ta bom
        }else{
            //Programar a Confirmação do Dado
        }

        val selectBebida = OpcaoBebida.checkedRadioButtonId
        val bebida =findViewById<RadioButton>(selectBebida)
        if (selectBebida == -1){
            Toast.makeText(this, "Avaliação da Proteina não Selecionado", Toast.LENGTH_SHORT).show()
            //Mensagem de Erro. ver se ja ta bom
        }else{
            //Programar a Confirmação do Dado
        }

        //navegateTelaConfirm()
    }
}