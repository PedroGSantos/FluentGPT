package com.example.fluentgpt

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.fluentgpt.adapter.ItemAdapter
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.data.MessageUser
import com.example.fluentgpt.data.MessagesTest
import com.example.fluentgpt.databinding.ActivityMainBinding
import com.example.fluentgpt.databinding.FragmentConversationsBinding
import com.example.fluentgpt.network.ApiService
import com.example.fluentgpt.network.retrofit
import kotlinx.coroutines.launch

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
        val myDataset = Datasource().loadConversations()
        itemAdapter = ItemAdapter(requireContext(), myDataset)
        binding.createConversationButton.setOnClickListener {
            val service = retrofit.create(ApiService::class.java)
            lifecycleScope.launch {
                try {
                    /*val post = Post(arrayOf(MessageUser("system", "You are a helpful assistant."),
                        MessageUser("user", "Who won the world series in 2020?"),
                        MessageUser("assistant", "The Los Angeles Dodgers won the World Series in 2020."),
                        MessageUser("user", "Where was it played?")))
                    val response = service.createChat(post)
                    Log.d("Pedro", response.body().toString());
                    Log.d("pedro", response.isSuccessful.toString())*/

                    val response = service.listModels();
                    Log.d("Pedro", response.body().toString())

                    val me = MessagesTest(arrayOf(MessageUser("system", "You are a helpful assistant."),
                        MessageUser("user", "Who won the world series in 2020?"),
                        MessageUser("assistant", "The Los Angeles Dodgers won the World Series in 2020."),
                        MessageUser("user", "Fale algo em portugues?")), "gpt-3.5-turbo");
                    val response2 = service.createChat(me);

                    Log.d("Pedro", response2.body().toString());
                } catch (e: Exception) {
                    Log.d("PEDRO", "me", e);
                    // Lidar com erros de rede ou exceções
                }
            }
        }
        binding.listConversations.adapter = itemAdapter
        binding.listConversations.setHasFixedSize(true)
    }

}