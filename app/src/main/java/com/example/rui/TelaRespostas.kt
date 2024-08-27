package com.example.rui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
        dbHelper = DBHelper(this)

        updateUI()

        binding.btVoltar.setOnClickListener{
            finish()
        }

        binding.BtnNutricionista.setOnClickListener {
            val intent = Intent(this, TelaNutricionista::class.java)
            startActivity(intent)
        }

        //DEBUG
        binding.ButaoTESTE.setOnClickListener {
            AdicionarExemplos()
        }

    }

    private fun AdicionarExemplos() {
        var email = "Test1@alu.ufc.br"
        var data = "27/08/24"
        var butaoapertado = "Carne Vermelha"
        var proteina = "Boa"
        var acompanhamento = "Boa"
        var bebida = "Boa"
        var sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Vermelha"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test3@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Vermelha"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test4@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Vermelha"
        proteina = "Ruim"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Vermelha"
        proteina = "Ruim"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Branca"
        proteina = "Boa"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Branca"
        proteina = "Boa"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Branca"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Branca"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Carne Branca"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Vegetariana"
        proteina = "Ruim"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Vegetariana"
        proteina = "Boa"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Vegetariana"
        proteina = "Boa"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Vegetariana"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)

        email = "Test2@alu.ufc.br"
        data = "27/08/24"
        butaoapertado = "Vegetariana"
        proteina = "Neutra"
        acompanhamento = "Boa"
        bebida = "Boa"
        sugestao = ""
        ADDBD(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)
    }
    private fun ADDBD(email:String, data:String, butaoapertado:String, proteina:String, acompanhamento:String, bebida:String, sugestao:String){
        dbHelper.insertData(email, data, butaoapertado, proteina, acompanhamento, bebida, sugestao)
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