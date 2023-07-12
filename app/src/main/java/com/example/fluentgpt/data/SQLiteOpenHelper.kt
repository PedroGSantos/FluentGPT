package com.example.fluentgpt.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createUserTableQuery = "CREATE TABLE user_data (id INTEGER PRIMARY KEY , weeksStudyRoutine INTEGER)"
        db.execSQL(createUserTableQuery)

        val createConversasTableQuery =
            "CREATE TABLE conversas (id INTEGER PRIMARY KEY AUTOINCREMENT, topic TEXT)"
        db.execSQL(createConversasTableQuery)

        val createMensagemTableQuery =
            "CREATE TABLE mensagem (id INTEGER PRIMARY KEY AUTOINCREMENT, ownerMessage INTEGER, mensagem TEXT, id_conversation INTEGER, FOREIGN KEY(id_conversation) REFERENCES conversas(id))"
        db.execSQL(createMensagemTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualize sua estrutura de banco de dados aqui, caso necessário
        if (oldVersion < 2) {
            // Exemplo de alteração na tabela
            val alterTableQuery = "ALTER TABLE minha_tabela ADD COLUMN sobrenome TEXT"
            db.execSQL(alterTableQuery)
        }
    }

    fun inserirDadosWeek(weeksStudyRoutine: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id", 1)
            put("weeksStudyRoutine", weeksStudyRoutine)
        }
        db.insert("user_data", null, values)
    }

    fun createConversation(topic: String){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("topic", topic)
        }
        db.insert("conversas", null, values)
    }

    fun atualizarDadosWeek(id: Int, weeksStudyRoutine: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("weeksStudyRoutine", weeksStudyRoutine)
        }
        val whereClause = "id = ?"
        val whereArgs = arrayOf(id.toString())
        db.update("user_data", values, whereClause, whereArgs)
    }

    @SuppressLint("Range")
    fun lerDadosWeekRoutine(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT weeksStudyRoutine FROM user_data WHERE id = ?", arrayOf("1"))
        if (cursor.moveToNext()) {
            val weeksStudyRoutine = cursor.getInt(cursor.getColumnIndex("weeksStudyRoutine"))
            cursor.close()
            return weeksStudyRoutine
        }
        cursor.close()
        return -1 // ou outro valor padrão caso o valor não seja encontrado
    }

    companion object {
        private const val DATABASE_NAME = "meu_banco_de_dados.db"
        private const val DATABASE_VERSION = 1
    }
}