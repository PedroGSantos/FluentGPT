package com.example.fluentgpt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fluentgpt.OpenConversation
import com.example.fluentgpt.R
import com.example.fluentgpt.model.Conversations

class ItemAdapter(private val context: Context, private val dataset: List<Conversations>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val cardMessage: RelativeLayout = view.findViewById(R.id.container_card_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_conversation, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.cardMessage.findViewById<TextView>(R.id.title_conversation).text =  item.titleConversation
        holder.cardMessage.findViewById<TextView>(R.id.last_message).text =  item.lastMessageConversation
        holder.cardMessage.setOnClickListener{
            val context = holder.cardMessage.context;
            val intent = Intent(context,OpenConversation::class.java)
            context.startActivity(intent)
        }

    }
}