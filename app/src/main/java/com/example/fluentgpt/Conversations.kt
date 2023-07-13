package com.example.fluentgpt

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.fluentgpt.adapter.ItemAdapter
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.data.HistoricMessages
import com.example.fluentgpt.data.MessageUser
import com.example.fluentgpt.data.SQLiteOpenHelper
import com.example.fluentgpt.databinding.FragmentConversationsBinding
import com.example.fluentgpt.network.GptApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Conversations.newInstance] factory method to
 * create an instance of this fragment.
 */

class Conversations : Fragment() {
    private lateinit var binding: FragmentConversationsBinding;
    private lateinit var itemAdapter: ItemAdapter;
    private val dbHelper by lazy { SQLiteOpenHelper(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: BINDING
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConversationsBinding.inflate(layoutInflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = Datasource(dbHelper).loadConversations()
        itemAdapter = ItemAdapter(requireContext(), myDataset)
        binding.listConversations.adapter = itemAdapter
        binding.listConversations.setHasFixedSize(true)

        binding.createConversationButton.setOnClickListener {
            val intent = Intent(binding.createConversationButton.context,OpenConversation::class.java)
            intent.putExtra("topic", binding.inputSubject.text.toString());
            val topic = intent.getStringExtra("topic")
            if (topic != null && topic != "") {
                val service = GptApi.retrofitService
                val conversationCreate = dbHelper.createConversation(topic)

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
                    dbHelper.addMessageToConversation(conversationCreate.toInt(), 1, response.body()?.choices?.get(0)?.message?.content.toString())

                    intent.putExtra("firstMessage", response.body()?.choices?.get(0)?.message?.content.toString())
                    intent.putExtra("idConversation", conversationCreate)
                    startActivity(intent)
                }
            }else{
                // Modal aqui
                showEmptyTopicDialog()
            }

        }


    }
    private fun showEmptyTopicDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Erro")
            .setMessage("O assunto não pode estar vazio.")
            .setPositiveButton("OK", null) // Você pode adicionar uma ação aqui, se necessário.
            .create()
            .show()
    }

}