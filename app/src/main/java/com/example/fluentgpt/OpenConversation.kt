package com.example.fluentgpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.fluentgpt.adapter.ItemAdapterMessage
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.data.HistoricMessages
import com.example.fluentgpt.data.MessageUser
import com.example.fluentgpt.databinding.ActivityOpenConversationBinding
import com.example.fluentgpt.model.Message
import com.example.fluentgpt.network.GptApi
import kotlinx.coroutines.launch

class OpenConversation : AppCompatActivity() {
    private lateinit var binding: ActivityOpenConversationBinding
    private lateinit var itemAdapter: ItemAdapterMessage;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_conversation)
        binding = ActivityOpenConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDataset = Datasource().loadMessages()
        itemAdapter = ItemAdapterMessage(this, myDataset)
        binding.listMessages.adapter = itemAdapter
        binding.listMessages.setHasFixedSize(true)

        val service = GptApi.retrofitService

        val topic = intent.getStringExtra("topic")

        binding.titleConversation.text = topic?.capitalize();

        var messagesConversation = HistoricMessages(
            mutableListOf(
                MessageUser(
                    "system",
                    "You are an English teacher, talk to the user and correct their answers, if necessary, teaching the correct part of the grammar. Talk only in English"
                ),
                MessageUser(
                "system",
                    "In this conversation, bring up a topic about $topic.When the user doesn't talk much, bring up another topic about $topic"
                ),
                MessageUser(
                    "system",
                    "In this conversation, don't give long answers, let the user speak"
                ),
                MessageUser(
                    "user",
                "Hello, lets talk about $topic"
                )
            ), "gpt-3.5-turbo"
        );

        lifecycleScope.launch {
            val response = service.createChat(messagesConversation);
            messagesConversation.messages.add(MessageUser("assistant", response.body()?.choices?.firstOrNull()?.message?.content.toString()))
            itemAdapter.addItem(
                Message(
                    1,
                    response.body()?.choices?.firstOrNull()?.message?.content
                )
            )

            itemAdapter.notifyDataSetChanged();
        }

        binding.sendMessageButton.setOnClickListener{
            messagesConversation.messages.add(MessageUser("user", binding.inputSubject.text.toString()))
            itemAdapter.addItem(
                Message(0,
                    binding.inputSubject.text.toString())
            )
            binding.inputSubject.text.clear()
            itemAdapter.notifyDataSetChanged()

            lifecycleScope.launch {
                val response = service.createChat(messagesConversation);
                Log.d("Pedro", messagesConversation.toString())
                messagesConversation.messages.add(MessageUser("user", response.body()?.choices?.firstOrNull()?.message?.content.toString()))
                itemAdapter.addItem(
                    Message(
                        1,
                        response.body()?.choices?.firstOrNull()?.message?.content
                    )
                )

                itemAdapter.notifyDataSetChanged();
            }
        }

        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}