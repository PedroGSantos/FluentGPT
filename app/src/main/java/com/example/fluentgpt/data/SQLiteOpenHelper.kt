package com.example.fluentgpt.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fluentgpt.model.Conversations
import com.example.fluentgpt.model.Message


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

    fun createConversation(topic: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("topic", topic)
        }
        val id = db.insert("conversas", null, values)
        return id
    }

    @SuppressLint("Range")
    fun getConversations(): List<Conversations> {
        val db = readableDatabase
        val conversationList = mutableListOf<Conversations>()

        val query = "SELECT conversas.id, topic, mensagem.mensagem " +
                "FROM conversas " +
                "LEFT JOIN (SELECT mensagem.id_conversation, mensagem.mensagem " +
                "           FROM mensagem " +
                "           WHERE mensagem.id IN (SELECT MAX(id) FROM mensagem GROUP BY id_conversation)) AS mensagem " +
                "ON conversas.id = mensagem.id_conversation"

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val topic = cursor.getString(cursor.getColumnIndex("topic"))
            val lastMessage = cursor.getString(cursor.getColumnIndex("mensagem"))
            val conversation = Conversations(topic, if (lastMessage == null) "Sem última mensagem" else lastMessage , id)
            conversationList.add(conversation)
        }

        cursor.close()
        return conversationList
    }

    fun addMessageToConversation(conversationId: Int, ownerMessage: Int, message: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id_conversation", conversationId)
            put("ownerMessage", ownerMessage)
            put("mensagem", message)
        }
        db.insert("mensagem", null, values)
    }

    @SuppressLint("Range")
    fun getMessagesFromConversation(conversationId: Int): MutableList<Message> {
        val db = readableDatabase
        val messageList = mutableListOf<Message>()

        val query = "SELECT ownerMessage, mensagem FROM mensagem WHERE id_conversation = $conversationId"

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val ownerMessage = cursor.getInt(cursor.getColumnIndex("ownerMessage"))
            val content = cursor.getString(cursor.getColumnIndex("mensagem"))

            val message = Message(ownerMessage, content)
            messageList.add(message)
        }

        cursor.close()
        return messageList
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