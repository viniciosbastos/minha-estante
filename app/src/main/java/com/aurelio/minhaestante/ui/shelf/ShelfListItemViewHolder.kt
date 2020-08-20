package com.aurelio.minhaestante.ui.shelf

import androidx.recyclerview.widget.RecyclerView
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.databinding.ShelfBookListItemBinding
import com.aurelio.minhaestante.domain.Book
import java.text.SimpleDateFormat

class ShelfListItemViewHolder(private val binding: ShelfBookListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.apply {
            authorText.text = book.author
            titleText.text = book.title
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            addedDate.text = itemView.context.getString(R.string.added_at, formatter.format(book.addedAt))
        }
    }
}