package com.aurelio.minhaestante.ui.reading

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.minhaestante.databinding.ReadingBookListItemBinding
import com.aurelio.minhaestante.domain.Book

class ReadingListItemViewHolder(
    private val binding: ReadingBookListItemBinding,
    private val listener: ReadingListAdapter.ReadingAdapterListener
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var book: Book

    fun bind(book: Book) {
        this.book = book
        binding.book = book
        binding.position = adapterPosition
        binding.listener = listener
        book.label?.let {
            binding.labelText.text = it.name
            binding.labelText.visibility = View.VISIBLE
        }
    }
}