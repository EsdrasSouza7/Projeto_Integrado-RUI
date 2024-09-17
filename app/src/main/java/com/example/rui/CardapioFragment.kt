package com.example.rui

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CardapioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cardapio, container, false)
        val dia = view.findViewById<TextView>(R.id.textoDia)
        val vermelha = view.findViewById<TextView>(R.id.conteudoCarneVermelha)
        val branca = view.findViewById<TextView>(R.id.conteudoCarneBranca)
        val vegetariana = view.findViewById<TextView>(R.id.conteudoCarneVegetariana)
        val acompanhamento = view.findViewById<TextView>(R.id.ConteudoAcompanhamento)
        val guarnicao = view.findViewById<TextView>(R.id.ConteudoGuarnicao)
        val salada = view.findViewById<TextView>(R.id.ConteudoSalada)
        val bebida = view.findViewById<TextView>(R.id.ConteudoBebida)
        val sobremesa = view.findViewById<TextView>(R.id.ConteudoSobremesa)

        arguments?.let {
            dia.text = it.getString(KEY_DIA)
            vermelha.text = it.getString(KEY_VERMELHA)
            branca.text = it.getString(KEY_BRANCA)
            vegetariana.text = it.getString(KEY_VEGETARIANA)
            acompanhamento.text = it.getString(KEY_ACOMPANHAMENTO)
            guarnicao.text = it.getString(KEY_GUARNICAO)
            salada.text = it.getString(KEY_SALADA)
            bebida.text = it.getString(KEY_BEBIDA)
            sobremesa.text = it.getString(KEY_SOBREMESA)
        }

        return view
    }

    companion object {

        fun newInstance(cardapio: DataCardapio): CardapioFragment {
            val args = Bundle()
            args.putString(KEY_DIA, cardapio.dia)
            args.putString(KEY_VERMELHA, cardapio.principalVer)
            args.putString(KEY_BRANCA, cardapio.principalBra)
            args.putString(KEY_VEGETARIANA, cardapio.principalVeg)
            args.putString(KEY_ACOMPANHAMENTO, cardapio.acompanhamento)
            args.putString(KEY_GUARNICAO, cardapio.guarnicao)
            args.putString(KEY_SALADA, cardapio.salada)
            args.putString(KEY_BEBIDA, cardapio.bebida)
            args.putString(KEY_SOBREMESA, cardapio.sobremesa)


            // Cria um novo WelcomeFragment e atribui o pacote com os dados para
            // serem recuperados na criacao do fragmento
            val fragment = CardapioFragment()
            fragment.arguments = args
            return fragment
        }

        const val KEY_DIA = "dia"
        const val KEY_VERMELHA = "CarneVermelha"
        const val KEY_BRANCA = "CarneBranca"
        const val KEY_VEGETARIANA = "Vegetariana"
        const val KEY_ACOMPANHAMENTO = "Acompanhamento"
        const val KEY_GUARNICAO = "Guranicao"
        const val KEY_SALADA = "Salada"
        const val KEY_BEBIDA = "Bebida"
        const val KEY_SOBREMESA = "Sobremesa"
    }

}