package com.example.fluentgpt.data

import android.util.Log
import com.example.fluentgpt.R
import com.example.fluentgpt.model.Conversations
import com.example.fluentgpt.model.Message
import com.google.gson.annotations.SerializedName


class Datasource(private val dbHelper: SQLiteOpenHelper) {

    fun loadConversations(): List<Conversations>{
        val conversations = dbHelper.getConversations()
        Log.d("TESTE", conversations.toString())
        return conversations
    }

    //TODO: Change this function to load historic messages from database when user open a conversation
    fun loadMessages(idConversation: Int): MutableList<Message> {
        val messages = dbHelper.getMessagesFromConversation(idConversation);
        return messages
    }
}

data class HistoricMessages(val messages: MutableList<MessageUser>, val model: String)

data class MessageUser(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
    )


data class ChatCompletionResponse(
    @SerializedName("choices")
    val choices: List<Choice>
)

data class Choice(
    @SerializedName("index")
    val index: Int,
    @SerializedName("message")
    val message: MessageResponse,
    @SerializedName("finish_reason")
    val finishReason: String
)

data class MessageResponse(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)
