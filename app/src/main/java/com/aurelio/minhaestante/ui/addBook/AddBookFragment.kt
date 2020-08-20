package com.aurelio.minhaestante.ui.addBook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.databinding.AddBookFragmentBinding
import com.aurelio.minhaestante.di.MinhaEstanteApplication
import com.aurelio.minhaestante.di.ViewModelProviderFactory
import com.aurelio.minhaestante.domain.Book
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class AddBookFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: AddBookViewModel by viewModels { viewModelFactory }
    private lateinit var binding: AddBookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MinhaEstanteApplication).appComponent.inject(this)
        binding = AddBookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            viewModel.addBook(Book(
                id = 1,
                title = binding.titleText.text.toString(),
                author = binding.authorText.text.toString(),
                pages = binding.pagesText.text.toString().toInt()
            ))
            findNavController().navigateUp()
        }
        dialog?.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                sheet.parent.parent.requestLayout()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheetDialogTheme)
    }
}