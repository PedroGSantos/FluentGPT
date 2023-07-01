package com.example.fluentgpt.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.fluentgpt.OpenConversation
import com.example.fluentgpt.R
import com.example.fluentgpt.model.Message
import org.w3c.dom.Text

class ItemAdapterMessage(private val context: Context, private val dataset: List<Message>): RecyclerView.Adapter<ItemAdapterMessage.ItemViewHolder>() {
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val cardMessage: ConstraintLayout = view.findViewById(R.id.message_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.message, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        if (item.ownerMessage == 0){
            Log.d("PEDRO", "Passou aq")
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_gpt).visibility = View.GONE
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_user).visibility = View.VISIBLE
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_user).findViewById<TextView>(R.id.message_user_content).text = context.resources.getString(item.messageId)
        } else {
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_gpt).visibility = View.VISIBLE
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_user).visibility = View.GONE
            holder.cardMessage.findViewById<ConstraintLayout>(R.id.message_gpt).findViewById<TextView>(R.id.message_gpt_content).text = context.resources.getString(item.messageId)
        }
    }
}