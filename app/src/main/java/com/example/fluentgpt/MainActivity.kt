package com.example.fluentgpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fluentgpt.adapter.ItemAdapter
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myDataset = Datasource().loadConversations();
        binding.listConversations.adapter = ItemAdapter(this, myDataset);
        binding.listConversations.setHasFixedSize(true)

    }
}