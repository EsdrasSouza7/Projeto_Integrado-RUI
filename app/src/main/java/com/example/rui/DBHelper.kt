package com.example.rui

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "formulario.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "AvaliacaoRU"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_DATA = "data"
        const val COLUMN_QPROTEINA = "qualProteina"
        const val COLUMN_RPROTEINA = "respostaProteina"
        const val COLUMN_ACOMPANHAMENTO = "acompanhamento"
        const val COLUMN_BEBIDA = "bebida"
        const val COLUMN_SUGESTAO = "sugestao"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_DATA + " TEXT, "
                + COLUMN_QPROTEINA + " TEXT, "
                + COLUMN_RPROTEINA + " TEXT, "
                + COLUMN_ACOMPANHAMENTO + " TEXT, "
                + COLUMN_BEBIDA + " TEXT, "
                + COLUMN_SUGESTAO + " TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(email: String,data: String, qProteina: String, rProteina: String, acompanhamento: String, bebida: String, sugestao: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_DATA, data)
        contentValues.put(COLUMN_QPROTEINA, qProteina)
        contentValues.put(COLUMN_RPROTEINA, rProteina)
        contentValues.put(COLUMN_ACOMPANHAMENTO, acompanhamento)
        contentValues.put(COLUMN_BEBIDA, bebida)
        contentValues.put(COLUMN_SUGESTAO, sugestao)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getAllData(): List<Respostas> {
        val list = mutableListOf<Respostas>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA))
                val qProteina = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QPROTEINA))
                val rProteina = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RPROTEINA))
                val acompanhamento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACOMPANHAMENTO))
                val bebida = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BEBIDA))
                val sugestao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUGESTAO))

                list.add(Respostas(id, email, data ,qProteina, rProteina, acompanhamento, bebida, sugestao))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
