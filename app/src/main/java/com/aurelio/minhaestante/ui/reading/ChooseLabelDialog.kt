package com.aurelio.minhaestante.ui.reading

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aurelio.minhaestante.databinding.ChooseLabelDialogBinding
import com.aurelio.minhaestante.di.MinhaEstanteApplication
import com.aurelio.minhaestante.di.ViewModelProviderFactory
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.ui.activity.LabelAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class ChooseLabelDialog : DialogFragment(), LabelAdapter.LabelListener {

    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory
    private val viewModel: ChooseLabelViewModel by viewModels { viewModelProvider }

    private lateinit var binding: ChooseLabelDialogBinding
    private val adapter = LabelAdapter(this)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Escolha um Rótulo")
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MinhaEstanteApplication).appComponent.inject(this)
        binding = ChooseLabelDialogBinding.inflate(layoutInflater, container, false)
        binding.chooseLabelsList.adapter = adapter
        viewModel.labels.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (showsDialog)
            (requireDialog() as AlertDialog).setView(binding.root)
    }

    override fun onLabelClicked(label: Label) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClicked(label: Label) {
        TODO("Not yet implemented")
    }
}