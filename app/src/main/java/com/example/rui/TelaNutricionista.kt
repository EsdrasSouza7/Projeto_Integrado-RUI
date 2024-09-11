package com.example.rui

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityTelaNutricionistaBinding

class TelaNutricionista : AppCompatActivity() {

    private lateinit var binding: ActivityTelaNutricionistaBinding
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaNutricionistaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)

        dbHelper = DBHelper(this)
        // Obter uma instância do banco de dados
        db = openOrCreateDatabase("formulario.db", MODE_PRIVATE, null)

        binding.btnNutriVoltar.setOnClickListener {
            finish()
        }

        // Suponha que você tenha um GridLayout no layout XML onde os botões serão adicionados
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        // Obtenha as datas distintas do banco de dados
        val dates = getDistinctDates()

        // Criar os botões dinamicamente
        for (date in dates) {
            // Cria um novo botão e personaliza o estilo
            val button = Button(this).apply {
                text = date
                setBackgroundColor(ContextCompat.getColor(this@TelaNutricionista, R.color.Azul1)) // Cor de fundo
                setTextColor(ContextCompat.getColor(this@TelaNutricionista, R.color.white)) // Cor do texto
                textSize = 18f
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Distribuição de largura
                    setMargins(8, 8, 8, 8) // Margens entre botões
                }
                // Opcional: Adicionar padding interno no botão
                setPadding(16, 16, 16, 16)
            }

            // Definir o que acontece quando o botão é clicado
            button.setOnClickListener {
                val intent = Intent(this, TelaDadosNutricionista::class.java)
                intent.putExtra("data", date)
                startActivity(intent)
            }

            // Adicionar o botão ao GridLayout
            gridLayout.addView(button)
        }

    }

    private fun getDistinctDates(): List<String> {
        val dates = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT DISTINCT data FROM AvaliacaoRU", null)
        if (cursor.moveToFirst()) {
            do {
                val date = cursor.getString(cursor.getColumnIndexOrThrow("data"))
                dates.add(date)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dates
    }

}