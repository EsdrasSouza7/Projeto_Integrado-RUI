package com.example.rui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cardapio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cardapio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)

        val contents = listOf(
            DataCardapio(
                dia = "Segunda Feira",
                principalVer = "Suino Fatiado Acebolado",
                principalBra = "Panqueca de Frango",
                principalVeg = "Almodega de Grão de Bico",
                salada = "Alface e Repolho",
                acompanhamento = "Arroz c/Passas e Feijão Fradinho",
                guarnicao = "Cuzcuz Amanteigado",
                bebida = "Suco de Manga",
                sobremesa = "Doce e Melão"
            ),
            DataCardapio(
                dia = "Terça Feira",
                principalVer = "Lasanha de Carne",
                principalBra = "Frango Grelhado",
                principalVeg = "Hambúrguer de Lentilha",
                salada = "Rúcula e Tomate",
                acompanhamento = "Arroz Integral e Feijão Preto",
                guarnicao = "Batata Doce Assada",
                bebida = "Suco de Abacaxi",
                sobremesa = "Salada de Frutas"
            ),
            DataCardapio(
                dia = "Quarta Feira",
                principalVer = "Carne Grelhado",
                principalBra = "Bife Acebolado",
                principalVeg = "Quibe de Abóbora",
                salada = "Alface e Cenoura Ralada",
                acompanhamento = "Arroz Branco e Feijão Carioca",
                guarnicao = "Farofa de Cenoura",
                bebida = "Suco de Laranja",
                sobremesa = "Gelatina e Uva",
            ),
            DataCardapio(
                dia = "Quinta Feira",
                principalVer = "Escondidinho de Carne",
                principalBra = "Filé de Peixe",
                principalVeg = "Gratinado de Batata e Espinafre",
                salada = "Acelga e Beterraba",
                acompanhamento = "Arroz à Grega e Lentilha",
                guarnicao = "Purê de Batata",
                bebida = "Suco de Maracujá",
                sobremesa = "Pudim de Leite",

            ),
            DataCardapio(
                dia = "Sexta Feira",
                principalVer = "Estrogonofe de Carne",
                principalBra = "Frango ao Molho Mostarda",
                principalVeg = "Bolinhos de Quinoa",
                salada = "Alface e Rabanete",
                acompanhamento = "Arroz Integral e Feijão Branco",
                guarnicao = "Purê de Mandioquinha",
                bebida = "Suco de Goiaba",
                sobremesa = "Mousse de Chocolate",

            ),
        )
        var contentIndex = 0

        replaceFragment(contents, contentIndex)

        val prevButton = findViewById<ImageButton>(R.id.btnAnteDia)
        val nextButton = findViewById<ImageButton>(R.id.btnProxDia)

        // Desativa o primeiro botao quando começamos
        prevButton.isEnabled = false

        prevButton.setOnClickListener {
            if (contentIndex > 0) {
                contentIndex-- // decrementa

                replaceFragment(contents, contentIndex)

                if (contentIndex == 0) {
                    prevButton.isEnabled = false
                }
                if (contentIndex == contents.size - 2) {
                    nextButton.isEnabled = true
                }
            }
        }

        nextButton.setOnClickListener {
            if (contentIndex < contents.size - 1) {
                contentIndex++

                replaceFragment(contents, contentIndex)

                if (contentIndex == contents.size - 1) {
                    nextButton.isEnabled = false
                }
                if (contentIndex == 1) {
                    prevButton.isEnabled = true
                }
            }
        }
    }

    private fun replaceFragment(contents: List<DataCardapio>, contentIndex: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.paginacaoCardapio, CardapioFragment.newInstance(contents[contentIndex]))
            .commit()
    }
}