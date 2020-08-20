package com.aurelio.minhaestante.ui.reading

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.ui.activity.SharedViewModel
import com.aurelio.minhaestante.databinding.AddProgressLayoutBinding
import com.aurelio.minhaestante.databinding.ReadingFragmentBinding
import com.aurelio.minhaestante.di.MinhaEstanteApplication
import com.aurelio.minhaestante.di.ViewModelProviderFactory
import com.aurelio.minhaestante.domain.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class ReadingFragment : Fragment(), ReadingListAdapter.ReadingAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: ReadingViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ReadingFragmentBinding

    private lateinit var listAdapter: ReadingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MinhaEstanteApplication).appComponent.inject(this)
        binding = ReadingFragmentBinding.inflate(inflater, container, false)
        listAdapter = ReadingListAdapter(this)
        binding.readingList.adapter = listAdapter

        return binding.root
    }

    private fun setUpObservers() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean  = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.RIGHT -> viewModel.shelfBook(viewHolder.adapterPosition)
                    ItemTouchHelper.LEFT -> viewModel.removeRook(viewHolder.adapterPosition)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    var icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_library_books_24)
                    var iconLeft = 0
                    var iconRight = 0

                    val background: ColorDrawable
                    val itemView = viewHolder.itemView
                    val margin = 32
                    val iconWidth = icon!!.intrinsicWidth
                    val iconHeight = icon.intrinsicHeight
                    val cellHeight = itemView.bottom - itemView.top
                    val iconTop = itemView.top + (cellHeight - iconHeight) / 2
                    val iconBottom = iconTop + iconHeight

                    // Right swipe.
                    if (dX > 0) {
                        icon = ContextCompat.getDrawable(context!!, R.drawable.ic_library_books_24)
                        background = ColorDrawable(resources.getColor(R.color.colorPrimary))
                        background.setBounds(
                            0,
                            itemView.top,
                            (itemView.left + dX).toInt(),
                            itemView.bottom
                        )
                        iconLeft = margin
                        iconRight = margin + iconWidth
                    } else {
                        icon = ContextCompat.getDrawable(context!!, R.drawable.ic_library_books_24)
                        background = ColorDrawable(Color.RED)
                        background.setBounds(
                            (itemView.right - dX).toInt(),
                            itemView.top,
                            0,
                            itemView.bottom
                        )
                        iconLeft = itemView.right - margin - iconWidth
                        iconRight = itemView.right - margin
                    }
                    background.draw(c)
                    icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                    icon?.draw(c)
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }
        }).attachToRecyclerView(binding.readingList)
        setUpObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun addProgress(position: Int) {
        val dialog = AddProgressLayoutBinding.inflate(LayoutInflater.from(requireContext()), view as ViewGroup, false)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Adicionar Progresso")
            .setView(dialog.root)
            .setPositiveButton("ADICIONAR") { _, _ ->
                run {
                    viewModel.addProgress(position, dialog.addProgressText.text.toString().toInt())
                }
            }
            .setNegativeButton("CANCELAR") { dialogInterface,_ ->
                run {
                    dialogInterface.dismiss()
                }
            }
            .show()
    }

    override fun addLabel(book: Book) {

    }
}