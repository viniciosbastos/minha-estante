package com.aurelio.minhaestante.ui.shelf

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
import androidx.recyclerview.widget.SimpleItemAnimator
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.ui.activity.SharedViewModel
import com.aurelio.minhaestante.databinding.ShelfFragmentBinding
import com.aurelio.minhaestante.di.MinhaEstanteApplication
import com.aurelio.minhaestante.di.ViewModelProviderFactory
import javax.inject.Inject

class ShelfFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: ShelfViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: SharedViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ShelfFragmentBinding
    private lateinit var listAdapter: ShelfListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MinhaEstanteApplication).appComponent.inject(this)
        binding = ShelfFragmentBinding.inflate(inflater, container, false)
        listAdapter = ShelfListAdapter()
        binding.listShelf.adapter = listAdapter
        binding.listShelf.setHasFixedSize(true)
        (binding.listShelf.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        Log.d("ViewModel", sharedViewModel.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> viewModel.readBook(viewHolder.adapterPosition)
                    ItemTouchHelper.RIGHT -> viewModel.deleteBook(viewHolder.adapterPosition)
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
                    var icon = ContextCompat.getDrawable(context!!, R.drawable.ic_library_books_24)
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
                        background = ColorDrawable(Color.RED)
                        background.setBounds(
                            0,
                            itemView.getTop(),
                            (itemView.getLeft() + dX).toInt(),
                            itemView.getBottom()
                        )
                        iconLeft = margin
                        iconRight = margin + iconWidth
                    } /*Left swipe.*/ else {
                        icon = ContextCompat.getDrawable(context!!, R.drawable.ic_library_books_24)
                        background = ColorDrawable(resources.getColor(R.color.colorPrimary))
                        background.setBounds(
                            (itemView.right - dX).toInt(),
                            itemView.getTop(),
                            0,
                            itemView.getBottom()
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
        }).attachToRecyclerView(binding.listShelf)
        setUpObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpObservers() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

//        sharedViewModel.label.observe(viewLifecycleOwner, Observer {
//            Log.d("SHELF_FRAGMENT", it.name)
//        })

    }
}