package com.example.fluentgpt.data

import com.example.fluentgpt.R
import com.example.fluentgpt.model.Conversations
import com.example.fluentgpt.model.Message
import com.google.gson.annotations.SerializedName

class Datasource {

    fun loadConversations(): List<Conversations>{
        return listOf<Conversations>(
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),
            Conversations(R.string.title_conversation, R.string.last_message),

        )
    }

    //TODO: Change this function to load historic messages from database when user open a conversation
    fun loadMessages(): MutableList<Message> {
        return mutableListOf<Message>()
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
