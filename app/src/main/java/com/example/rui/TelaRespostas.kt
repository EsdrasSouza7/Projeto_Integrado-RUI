package com.example.rui

import android.content.Intent
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
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

        val linearRespostas = findViewById<LinearLayout>(R.id.LinearLayouRespostas)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)
        dbHelper = DBHelper(this)

        val respostas = dbHelper.getAllData()
        for (resposta in respostas) {
            val textView = TextView(this)
            val texto = "Data: ${resposta.data}\nEmail: ${resposta.email}\nCarne Escolhida: ${resposta.qProteina}\nAvaliações:\nProteina: ${resposta.rProteina}\nAcompanhamento: ${resposta.acompanhamento}\nBebida: ${resposta.bebida}\nSugestão: ${resposta.sugestao}".also { textView.text = it }
            textView.text = texto
            textView.background = ContextCompat.getDrawable(
                this@TelaRespostas,
                R.drawable.background_button_enable
            ) // Cor de fundo
            textView.setTextColor(
                ContextCompat.getColor(
                    this@TelaRespostas,
                    R.color.white
                )
            ) // Cor do texto
            textView.textSize = 18f
            textView.gravity = Gravity.CENTER
            textView.typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
            textView.setPadding(10,10,10,10)


            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(50, 20, 50, 5)

            textView.layoutParams = layoutParams

            linearRespostas.addView(textView)

        }
        binding.btVoltar.setOnClickListener {
            finish()
        }
    }
}