package com.aurelio.minhaestante.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.minhaestante.databinding.ItemLabelListBinding
import com.aurelio.minhaestante.domain.Label

class LabelAdapter(
    private val listener: LabelListener
) : ListAdapter<Label, LabelAdapter.LabelViewHolder>(LabelDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        return LabelViewHolder(
            ItemLabelListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LabelViewHolder (
        private val binding: ItemLabelListBinding,
        private val listener: LabelListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(label: Label) {
            binding.plusIc.visibility = if (adapterPosition == 0) View.VISIBLE else View.GONE
            binding.labelColor.visibility = if (adapterPosition == 0) View.INVISIBLE else View.VISIBLE
            binding.removeButton.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
            binding.label = label
            binding.position = adapterPosition
            binding.listener = listener
        }
    }

    interface LabelListener {
        fun onLabelClicked(label: Label)
        fun onDeleteClicked(label: Label)
    }
}

class LabelDiff : DiffUtil.ItemCallback<Label>() {
    override fun areItemsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem == newItem
    }
}