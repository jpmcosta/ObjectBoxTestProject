package com.example.jpmcosta.objectboxtestproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.NO_ID
import android.view.ViewGroup
import android.widget.TextView
import com.example.jpmcosta.objectboxtestproject.R
import com.example.jpmcosta.objectboxtestproject.objectbox.Item
import com.example.jpmcosta.objectboxtestproject.objectbox.Note

open class ItemListAdapter : RecyclerView.Adapter<ItemListAdapter.ItemHolder>() {

    init {
        setHasStableIds(true)
    }

    var items: List<Item>? = null
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    private var mOnItemClickListener: OnItemClickListener? = null


    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun getItemId(position: Int): Long = items?.get(position)?.id ?: NO_ID.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
            ItemHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.adapter_item, parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        items?.get(position)?.let { item ->
            holder.bind(item)
        }
    }

    override fun onViewRecycled(holder: ItemHolder) {
        holder.unbind()
    }


    inner class ItemHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var item: Item? = null

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(item: Item) {
            this.item = item
            onBind(item.id, item.isActive, item.notes)
        }

        fun unbind() {
            item = null
        }

        private fun onBind(id: Long, isActive: Boolean, notes: List<Note>?) {
            var text = "Item: $id ${if (isActive) "active" else "inactive"}"

            notes?.forEach { note ->
                text += "\nNote: ${note.text}"
            }

            (itemView as TextView).text = text
        }

        override fun onClick(v: View) {
            if (mOnItemClickListener != null) {
                item?.let { item ->
                    mOnItemClickListener!!.onItemClicked(item)
                }
            }
        }
    }

    interface OnItemClickListener {

        fun onItemClicked(item: Item)
    }
}