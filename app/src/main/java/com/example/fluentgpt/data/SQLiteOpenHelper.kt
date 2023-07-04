package com.example.fluentgpt.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createUserTableQuery = "CREATE TABLE user_data (id INTEGER PRIMARY KEY, weeksStudyRoutine INTEGER, )"
        db.execSQL(createUserTableQuery)

        val createConversasTableQuery =
            "CREATE TABLE conversas (id INTEGER PRIMARY KEY, usuario_id INTEGER, mensagem TEXT, FOREIGN KEY(usuario_id) REFERENCES usuario(id))"
        db.execSQL(createConversasTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualize sua estrutura de banco de dados aqui, caso necessário
        if (oldVersion < 2) {
            // Exemplo de alteração na tabela
            val alterTableQuery = "ALTER TABLE minha_tabela ADD COLUMN sobrenome TEXT"
            db.execSQL(alterTableQuery)
        }
    }

    fun inserirDados(nome: String?) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nome", nome)
        db.insert("minha_tabela", null, values)
    }

    @SuppressLint("Range")
    fun lerDados(): List<String> {
        val db = readableDatabase
        val nomes: MutableList<String> = ArrayList()
        val cursor = db.rawQuery("SELECT nome FROM minha_tabela", null)
        while (cursor.moveToNext()) {
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            nomes.add(nome)
        }
        cursor.close()
        return nomes
    }

    companion object {
        private const val DATABASE_NAME = "meu_banco_de_dados.db"
        private const val DATABASE_VERSION = 1
    }
}