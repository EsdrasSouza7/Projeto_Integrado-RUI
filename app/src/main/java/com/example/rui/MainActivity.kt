package com.example.rui

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ScheduleExactAlarm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = ContextCompat.getColor(this, R.color.BackGround)


        // Agendar a primeira verificação
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Agendar para rodar imediatamente
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent)

        binding.BtnSobre.setOnClickListener {
            sobre()
        }

        binding.BtAvaliar.setOnClickListener {
            avaliar()
        }

        binding.BtEntrar.setOnClickListener {
            cardapio()
        }
        binding.BtnRespostas.setOnClickListener {
            respostas()
        }
        binding.BtnNutricionista.setOnClickListener {
            nutricionista()
        }
    }

    private fun sobre() {
        val intent = Intent(this, TelaSobre::class.java)
        startActivity(intent)
    }

    private fun nutricionista() {
        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtnNutricionista.isEnabled = false
        binding.BtnNutricionista.setTextColor(Color.BLACK)

        Handler(Looper.getMainLooper()).postDelayed({ ativarButoes() }, 500)
        Handler(Looper.getMainLooper()).postDelayed({ navegateTelaNutricionista() }, 100)
    }

    private fun navegateTelaNutricionista() {
        val intent = Intent(this, TelaNutricionista::class.java)
        startActivity(intent)
    }

    private fun cardapio() {
        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtnNutricionista.isEnabled = false
        binding.BtEntrar.setTextColor(Color.BLACK)

        Handler(Looper.getMainLooper()).postDelayed({ ativarButoes() }, 500)
        Handler(Looper.getMainLooper()).postDelayed({ navegateTelaCardapio() }, 100)
    }

    private fun navegateTelaCardapio() {
        val intent = Intent(this, Cardapio::class.java)
        startActivity(intent)
    }

    private fun avaliar() {
        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtnNutricionista.isEnabled = false
        binding.BtAvaliar.setTextColor(Color.BLACK)

        Handler(Looper.getMainLooper()).postDelayed({ ativarButoes() }, 500)
        Handler(Looper.getMainLooper()).postDelayed({ navegateTelaFormulario() }, 100)

    }

    private fun navegateTelaFormulario() {
        val intent = Intent(this, TelaFormulario::class.java)
        startActivity(intent)
    }

    private fun respostas() {
        binding.BtEntrar.isEnabled = false
        binding.BtAvaliar.isEnabled = false
        binding.BtnRespostas.isEnabled = false
        binding.BtnNutricionista.isEnabled = false
        binding.BtnRespostas.setTextColor(Color.BLACK)

        Handler(Looper.getMainLooper()).postDelayed({ ativarButoes() }, 500)
        Handler(Looper.getMainLooper()).postDelayed({ navegateTelaRespostas() }, 500)
    }

    private fun navegateTelaRespostas() {
        val intent = Intent(this, TelaRespostas::class.java)
        startActivity(intent)
    }

    private fun ativarButoes() {
        binding.BtAvaliar.isEnabled = true
        binding.BtEntrar.isEnabled = true
        binding.BtnRespostas.isEnabled = true
        binding.BtnNutricionista.isEnabled = true
        binding.BtAvaliar.setTextColor(Color.WHITE)
        binding.BtEntrar.setTextColor(Color.WHITE)
        binding.BtnRespostas.setTextColor(Color.WHITE)
        binding.BtnNutricionista.setTextColor(Color.WHITE)
    }
}