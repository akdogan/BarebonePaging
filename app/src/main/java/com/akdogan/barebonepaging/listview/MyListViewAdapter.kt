package com.akdogan.barebonepaging.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akdogan.barebonepaging.R
import com.akdogan.barebonepaging.repository.FantasyCharacter

class MyListViewAdapter : PagingDataAdapter<FantasyCharacter, MyListViewAdapter.MyListViewHolder>(
    CHARACTER_COMPARATOR
) {

    class MyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumb = itemView.findViewById<ImageView>(R.id.listview_thumbnail)
        val name = itemView.findViewById<TextView>(R.id.listview_name)
        val charClass = itemView.findViewById<TextView>(R.id.listview_class)
        val race = itemView.findViewById<TextView>(R.id.listview_race)
        val info = itemView.findViewById<TextView>(R.id.listview_info)

        fun bind(item: FantasyCharacter?) {
            if (item == null) {
                name.text = "EMPTY ITEM"
            } else {
                thumb.setImageResource(item.thumbnail)
                name.text = "${item.name}"
                charClass.text = item.characterClass
                race.text = item.race
                info.text = item.id//"${item.age}, ${item.sex}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_item, parent, false)

        return MyListViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<FantasyCharacter>() {
            override fun areItemsTheSame(
                oldItem: FantasyCharacter,
                newItem: FantasyCharacter
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FantasyCharacter,
                newItem: FantasyCharacter
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}