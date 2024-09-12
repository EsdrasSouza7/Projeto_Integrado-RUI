package com.example.rui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityTelaFormularioBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TelaFormulario : AppCompatActivity() {

    private lateinit var binding: ActivityTelaFormularioBinding
    private lateinit var dbHelper: DBHelper
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
        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)

        dbHelper = DBHelper(this)

        var butaoApertado = "None"

        binding.btnVoltar.setOnClickListener {
            finish()
        }

        getData()
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
            confirmEnviar(butaoApertado)

        }
    }

    private fun confirmEnviar(butaoApertado: String) {
        val opcaoProteina = findViewById<RadioGroup>(R.id.AvaliacaoCarne)
        val opcaoAcompanhamento = findViewById<RadioGroup>(R.id.AvaliacaoAcompanhamento)
        val campoEmail = findViewById<EditText>(R.id.EmailInstitucional)

        val erroNaoSelecionado = AlertDialog.Builder(this)
        erroNaoSelecionado.setNeutralButton("Cancelar", null)

        if (campoEmail.text.contains("@alu.ufc.br") or campoEmail.text.contains("@ufc.br")){
            campoEmail.setTextColor(1234123123)
        }else{
            erroNaoSelecionado.setMessage("Email icompativel. \n Use um Email instucional da UFC")
            erroNaoSelecionado.show()
            return
        }

        val selectProteina = opcaoProteina.checkedRadioButtonId
        if (selectProteina == -1){
            erroNaoSelecionado.setMessage("Avaliação da proteina não selecionada.")
            erroNaoSelecionado.show()
            return
            //Mensagem de Erro.
        }

        val selectAcompanhamento = opcaoAcompanhamento.checkedRadioButtonId
        if (selectAcompanhamento == -1){
            erroNaoSelecionado.setMessage("Avaliação do acompanhamento não selecionada.")
            erroNaoSelecionado.show()
            return
            //Mensagem de Erro.
        }

        val confirmEnvio = AlertDialog.Builder(this)
        confirmEnvio.setMessage("Confirmar envio?")
        confirmEnvio.setNeutralButton("Cancelar", null)
        confirmEnvio.setPositiveButton("Enviar") { _: DialogInterface?, _: Int -> enviar(butaoApertado) }
        confirmEnvio.show()
    }

    private fun ativar(){
        val caixa = binding.caixaDePergunta
        caixa.visibility = View.VISIBLE
    }
    private fun getData(){
        val dia = SimpleDateFormat("dd", Locale.US)
        val data = Date()
        binding.DataAtual.text = "${dia.format(data)} de"

        val mes = SimpleDateFormat("MM", Locale.US)
        when {
            mes.format(data) == "01" -> {
                binding.DataAtual2.text = "Janeiro"
            }
            mes.format(data) == "02" -> {
                binding.DataAtual2.text = "Fevereiro"
            }
            mes.format(data) == "03" -> {
                binding.DataAtual2.text = "Março"
            }
            mes.format(data) == "04" -> {
                binding.DataAtual2.text = "Abril"
            }
            mes.format(data) == "05" -> {
                binding.DataAtual2.text = "Maio"
            }
            mes.format(data) == "06" -> {
                binding.DataAtual2.text = "Junho"
            }
            mes.format(data) == "07" -> {
                binding.DataAtual2.text = "Julho"
            }
            mes.format(data) == "08" -> {
                binding.DataAtual2.text = "Agosto"
            }
            mes.format(data) == "09" -> {
                binding.DataAtual2.text = "Setembro"
            }
            mes.format(data) == "10" -> {
                binding.DataAtual2.text = "Outubro"
            }
            mes.format(data) == "11" -> {
                binding.DataAtual2.text = "Novembro"
            }
            mes.format(data) == "12" -> {
                binding.DataAtual2.text = "Dezembro"
            }
        }
    }
    private fun enviar(butaoapertado: String){
        val opcaoProteina = findViewById<RadioGroup>(R.id.AvaliacaoCarne)
        val opcaoAcompanhamento = findViewById<RadioGroup>(R.id.AvaliacaoAcompanhamento)
        val opcaoBebida = findViewById<RadioGroup>(R.id.Avaliacaobebida)
        val campoEmail = findViewById<EditText>(R.id.EmailInstitucional)
        val campoSugestao = findViewById<EditText>(R.id.editTextSugestao)

        val email = campoEmail.text.toString()
        val proteina: String
        val acompanhamento: String
        val bebida: String
        var sugestao = campoSugestao.text.toString()
        val data = getDataCompleta()


        val selectProteina = opcaoProteina.checkedRadioButtonId
        val btnproteina = findViewById<RadioButton>(selectProteina)
        if (selectProteina == -1){
            Toast.makeText(this, "Avaliação da Proteina não Selecionado", Toast.LENGTH_SHORT).show()
            return
            //Mensagem de Erro.
        }else{
            proteina = btnproteina.text.toString()
        }

        val selectAcompanhamento = opcaoAcompanhamento.checkedRadioButtonId
        val btnacompanhamento = findViewById<RadioButton>(selectAcompanhamento)
        if (selectAcompanhamento == -1){
            Toast.makeText(this, "Avaliação da Acompanhamento não Selecionado", Toast.LENGTH_SHORT).show()
            return
            //Mensagem de Erro.
        }else{
            acompanhamento = btnacompanhamento.text.toString()
        }

        val selectBebida = opcaoBebida.checkedRadioButtonId
        val btnbebida =findViewById<RadioButton>(selectBebida)
        bebida = if (selectBebida == -1){
            "Não Respondido"
        }else{
            btnbebida.text.toString()
        }

        if (sugestao.isEmpty()){
            sugestao = "Em Branco"
        }

        val isInsert = dbHelper.insertData(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)
        if (isInsert){
            val envioBemSucedido = AlertDialog.Builder(this)
            envioBemSucedido.setMessage("Avaliação bem sucedida")
            envioBemSucedido.setPositiveButton("Cardapio"){_: DialogInterface?, _: Int -> navegarCardapio()}
            envioBemSucedido.setNegativeButton("Menu Inicial"){_: DialogInterface?, _: Int -> finish()}
            envioBemSucedido.show()
        }else{
            Toast.makeText(this, "Erro ao Inserir Dados", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navegarCardapio() {
        val intent = Intent(this, Cardapio::class.java)
        startActivity(intent)
        finish()
    }

    private fun getDataCompleta(): String {
        val formatData = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val data = Date()
        return formatData.format(data)
    }
}