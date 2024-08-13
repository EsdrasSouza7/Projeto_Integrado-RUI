package com.example.rui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityCardapioBinding
import com.example.rui.databinding.ActivityTelaConfirmBinding
import com.example.rui.databinding.ActivityTelaFormularioBinding

class TelaCardapio : AppCompatActivity() {
    private lateinit var binding: ActivityCardapioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardapioBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cardapio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }
}