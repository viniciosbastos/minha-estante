package com.aurelio.minhaestante.ui.reading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aurelio.minhaestante.databinding.ReadingBookListItemBinding
import com.aurelio.minhaestante.domain.Book

class ReadingListAdapter (
    private val listener: ReadingAdapterListener
): ListAdapter<Book, ReadingListItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingListItemViewHolder {
        return ReadingListItemViewHolder(
            ReadingBookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ReadingListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ReadingAdapterListener {
        fun addProgress(position: Int)
        fun addLabel(book: Book)
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}