package com.cibo.cibo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Item

class ItemsAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            view.findViewById<CardView>(R.id.card_parent).setOnClickListener {
                itemClickListener.itemClick(item)
            }
            view.findViewById<TextView>(R.id.tv_food_name).text = item.content
            Glide.with(context).load(item.img).into(view.findViewById(R.id.iv_food_photo))
        }
    }

    interface ItemClickListener {
        fun itemClick(item: Item)
    }
}