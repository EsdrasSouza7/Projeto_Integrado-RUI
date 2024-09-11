package com.example.rui

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class TelaDadosNutricionista : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var db: SQLiteDatabase
    private lateinit var barChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_dados_nutricionista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)

        pieChart = findViewById(R.id.pieChart)
        barChart = findViewById(R.id.barChart)

        val data = intent.getStringExtra("data")
        val textoData = findViewById<TextView>(R.id.txtDia)
        textoData.text = data

        // Obter uma instância do banco de dados
        db = openOrCreateDatabase("formulario.db", MODE_PRIVATE, null)

        // Configurar o gráfico de pizza
        if (data != null) {
            setupPieChart(data)
        }

        // Configurar o gráfico de barras empilhadas
        if (data != null) {
            setupStackedBarChart(data)
        }

        val btnVoltar = findViewById<ImageButton>(R.id.btVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun contadorDeStringIguais(cursor: Cursor, colunaDB: String): MutableMap<String, Float> {
        val contagemPalavras = mutableMapOf<String, Float>()
        val listaPalavras: MutableList<String> = mutableListOf()

        if (cursor.moveToFirst()) {
            do {
                listaPalavras.add(cursor.getString(cursor.getColumnIndexOrThrow(colunaDB)))
            } while (cursor.moveToNext())
        }
        contagemPalavras["Boa"] = 0f
        contagemPalavras["Neutra"] = 0f
        contagemPalavras["Ruim"] = 0f
        for (palavra  in listaPalavras) {
            val count = contagemPalavras.getOrDefault("Boa", 0f)
            if (palavra == "Boa"){
                contagemPalavras[palavra] = count + 1f
            }
        }
        for (palavra  in listaPalavras) {
            val count = contagemPalavras.getOrDefault("Neutra", 0f)
            if (palavra == "Neutra"){
                contagemPalavras[palavra] = count + 1f
            }
        }
        for (palavra  in listaPalavras) {
            val count = contagemPalavras.getOrDefault("Ruim", 0f)
            if (palavra == "Ruim"){
                contagemPalavras[palavra] = count + 1f
            }
        }
        return contagemPalavras
    }

    private fun setupPieChart(dataDados: String) {
        // Buscar os dados do banco de dados SQLite
        val colors = mutableListOf<Int>(
            ColorTemplate.MATERIAL_COLORS[0],
            ColorTemplate.MATERIAL_COLORS[1],
            ColorTemplate.MATERIAL_COLORS[2])
        val pieEntries = mutableListOf<PieEntry>()
        val cursor = db.rawQuery("SELECT respostaProteina FROM AvaliacaoRU WHERE data = '$dataDados'", null)

        val contagemPalavras = contadorDeStringIguais(cursor, "respostaProteina")
        cursor.close()

        //adicionando os dados
        for ((palavra, count) in contagemPalavras) {
            pieEntries.add(PieEntry(count, palavra))
        }

        // Configurar o dataset para o gráfico de pizza
        val dataSet = PieDataSet(pieEntries, "Qualidades Proteinas")
        dataSet.colors = colors // Definir cores
        dataSet.valueTextSize = 13f // Tamanho do texto opcional
        dataSet.valueFormatter = DefaultValueFormatter(0) // Formatação opcional

        // Criar o objeto PieData e associá-lo ao gráfico
        val pieData = PieData(dataSet)
        pieChart.data = pieData

        // Configurar outras propriedades do gráfico
        pieChart.description.isEnabled = false // Desativar a descrição
        pieChart.centerText = "Notas da Qualidade das Proteinas" // Texto central
        pieChart.setEntryLabelColor(android.R.color.black) // Cor dos labels

        pieChart.invalidate() // Atualizar o gráfico
    }

    private fun setupStackedBarChart(dataDados: String) {
        // Criar os dados de entrada para o gráfico
        val barEntries = mutableListOf<BarEntry>()
        val colors = mutableListOf<Int>(
            ColorTemplate.MATERIAL_COLORS[0],
            ColorTemplate.MATERIAL_COLORS[1],
            ColorTemplate.MATERIAL_COLORS[2])
//        var Notas = mutableListOf<Float>()
        val notas1 = floatArrayOf(0f, 0f, 0f)
        val notas2 = floatArrayOf(0f, 0f, 0f)
        val notas3 = floatArrayOf(0f, 0f, 0f)
        var b = 0
        var n = 0
        var r = 0

        //Carne Vermelha
        var cursor = db.rawQuery("SELECT qualProteina, respostaProteina FROM AvaliacaoRU WHERE data = '$dataDados' AND qualProteina = 'Carne Vermelha'", null)
        var contagemPalavras = contadorDeStringIguais(cursor, "respostaProteina")
        cursor.close()
        for ((palavra, count) in contagemPalavras) {
            when (palavra) {
                "Boa" -> {
                    notas1[0] = count.toFloat()
                }
                "Neutra" -> {
                    notas1[1] = count.toFloat()
                }
                "Ruim" -> {
                    notas1[2] = count.toFloat()
                }
            }
        }
        barEntries.add(BarEntry(0f, notas1))

        //Carne Branca
        cursor = db.rawQuery("SELECT qualProteina, respostaProteina FROM AvaliacaoRU WHERE data = '$dataDados' AND qualProteina = 'Carne Branca'", null)
        contagemPalavras = contadorDeStringIguais(cursor, "respostaProteina")
        cursor.close()
        for ((palavra, count) in contagemPalavras) {
            when (palavra) {
                "Boa" -> {
                    notas2[0] = count.toFloat()
                    b = 1
                }
                "Neutra" -> {
                    notas2[1] = count.toFloat()
                    n = 1
                }
                "Ruim" -> {
                    notas2[2] = count.toFloat()
                    r = 1
                }
            }
        }
        if (b==0){
            notas2[0] = 0f
        }
        if (n==0){
            notas2[1] = 0f
        }
        if (r==0){
            notas2[2] = 0f
        }
        barEntries.add(BarEntry(1f, notas2))

        //Vegetariana
        cursor = db.rawQuery("SELECT qualProteina, respostaProteina FROM AvaliacaoRU WHERE data = '$dataDados' AND qualProteina = 'Vegetariana'", null)
        contagemPalavras = contadorDeStringIguais(cursor, "respostaProteina")
        cursor.close()
        b=0
        n=0
        r=0
        for ((palavra, count) in contagemPalavras) {
            when (palavra) {
                "Boa" -> {
                    notas3[0] = count.toFloat()
                    b = 1
                }
                "Neutra" -> {
                    notas3[1] = count.toFloat()
                    n = 1
                }
                "Ruim" -> {
                    notas3[2] = count.toFloat()
                    r = 1
                }
            }
        }
        if (b==0){
            notas3[0] = 0f
        }
        if (n==0){
            notas3[1] = 0f
        }
        if (r==0){
            notas3[2] = 0f
        }
        barEntries.add(BarEntry(2f, notas3))

        //Criar o dataset
        val dataSet = BarDataSet(barEntries, "Notas Todas As Proteinas")
        dataSet.colors = colors // Definir cores para cada segmento da barra
        dataSet.setStackLabels(arrayOf("Bom", "Neutro", "Ruim")) // Definir labels para as stacks

        //Numeros no meio das barras
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 13f // Tamanho do texto opcional
        dataSet.valueFormatter = DefaultValueFormatter(0) // Formatação opcional

        //Descrição embaixo da barra
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("Carne Vermelha", "Carne Branca", "Vegetariana"))
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f // Define a granularidade para 1, para que cada barra tenha uma descrição
        xAxis.setDrawLabels(true)

        // Criar o objeto BarData e associá-lo ao gráfico
        val barData = BarData(dataSet)
        barData.barWidth = 0.5f // Definir a largura das barras

        barChart.data = barData

        // Configurar propriedades adicionais do gráfico
        barChart.description.isEnabled = false // Desativar a descrição do gráfico
        barChart.setFitBars(true) // Ajustar as barras dentro do gráfico

        barChart.invalidate() // Atualizar o gráfico
    }
}