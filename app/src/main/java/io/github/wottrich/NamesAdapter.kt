package io.github.wottrich

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wottrich.databinding.RowNameBinding

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 17/04/2021
 *
 * Copyright © 2021 AndroidFaculdadeUnidade2. All rights reserved.
 *
 */
 
class NamesAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<String>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NameViewHolder.new(parent.context)
    }

    private fun getItem(position: Int) =
        items[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NameViewHolder) {
            holder.bind(getItem(position)) {
                onClick(it)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

class NameViewHolder(private val binding: RowNameBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(name: String, onClick: (String) -> Unit) {
        binding.apply {
            textViewName.text = name
        }
        itemView.setOnClickListener {
            onClick(binding.textViewName.text.toString())
        }
    }

    companion object {
        fun new(context: Context): NameViewHolder {
            val inflater = LayoutInflater.from(context)
            return NameViewHolder(RowNameBinding.inflate(inflater))
        }
    }

}