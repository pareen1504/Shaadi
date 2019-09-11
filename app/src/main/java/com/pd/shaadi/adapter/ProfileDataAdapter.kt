package com.pd.shaadi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pd.shaadi.databinding.RowItemBinding
import com.pd.shaadi.model.database.DbData

class ProfileDataAdapter(val matchProfileClickListener: MatchProfileClickListener) :
    ListAdapter<DbData, ProfileDataAdapter.MyViewHolder>(ProfileDataScanCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, matchProfileClickListener)
    }

    class MyViewHolder private constructor(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DbData, matchProfileClickListener: MatchProfileClickListener) {
            binding.recylerListdata = item
            binding.buttonClicklistener = matchProfileClickListener
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    interface MatchProfileClickListener {
        fun accept(dbData: DbData)
        fun reject(dbData: DbData)

    }


    class ProfileDataScanCallbacks : DiffUtil.ItemCallback<DbData>() {
        override fun areItemsTheSame(oldItem: DbData, newItem: DbData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DbData, newItem: DbData): Boolean {
            return oldItem.selection == newItem.selection
        }

    }
}