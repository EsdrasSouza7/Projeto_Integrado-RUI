package com.example.rui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityCardapio2Binding

class TelaCardapio2 : AppCompatActivity() {
    private lateinit var binding: ActivityCardapio2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardapio2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cardapio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            val intent = Intent(this, TelaCardapio::class.java)
            startActivity(intent)
            finish()
        }
    }
}