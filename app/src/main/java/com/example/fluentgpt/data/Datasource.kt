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

    fun loadMessages(): List<Message> {
        return listOf<Message>(
            Message(1, R.string.last_message ),
            Message(0, R.string.last_message),
            Message(0, R.string.last_message),
            Message(1, R.string.last_message),
            Message(1, R.string.last_message),
            Message(1, R.string.last_message),
            Message(0, R.string.last_message),
            Message(1, R.string.last_message),
            Message(1, R.string.last_message)

        )
    }


}

data class MessagesTest(val messages: Array<MessageUser>, val model: String)

data class MessageUser(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
    )

data class Model(
    val id: String,
    @SerializedName("object")
    val objectType: String,
    val owned_by: String,
)

data class Response(
    val data: List<Model>,
    val `object`: String
)

data class ChatCompletionResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("object")
    val objectType: String,
    @SerializedName("created")
    val created: Long,
    @SerializedName("choices")
    val choices: List<Choice>,
    @SerializedName("usage")
    val usage: Usage
)

data class Choice(
    @SerializedName("index")
    val index: Int,
    @SerializedName("message")
    val message: Messagee,
    @SerializedName("finish_reason")
    val finishReason: String
)

data class Messagee(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)