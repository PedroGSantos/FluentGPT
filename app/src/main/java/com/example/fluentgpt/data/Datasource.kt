package com.example.fluentgpt.data

import com.example.fluentgpt.R
import com.example.fluentgpt.model.Conversations

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

}