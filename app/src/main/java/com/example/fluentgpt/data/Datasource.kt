package com.example.fluentgpt.data

import com.example.fluentgpt.R
import com.example.fluentgpt.model.Conversations
import com.example.fluentgpt.model.Message

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