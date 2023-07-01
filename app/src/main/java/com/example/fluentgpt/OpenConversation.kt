package com.example.fluentgpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fluentgpt.adapter.ItemAdapter
import com.example.fluentgpt.adapter.ItemAdapterMessage
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.databinding.ActivityMainBinding
import com.example.fluentgpt.databinding.ActivityOpenConversationBinding

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

        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}