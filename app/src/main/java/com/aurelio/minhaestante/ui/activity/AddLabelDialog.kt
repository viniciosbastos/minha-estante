package com.aurelio.minhaestante.ui.activity

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.aurelio.minhaestante.R
import com.aurelio.minhaestante.databinding.FragmentAddLabelDialogBinding
import com.aurelio.minhaestante.domain.Label
import com.aurelio.minhaestante.domain.LabelColorEnum
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddLabelDialog(
    private val listener: AddLabelDialog.Listener,
    private val context: Context,
    private val viewGroup: ViewGroup
) : ChooseColorListener{

    private lateinit var dialogBinding: FragmentAddLabelDialogBinding
    private var selectedColorView: View? = null
    private lateinit var dialog: AlertDialog

    interface Listener {
        fun addLabel(label: Label)
    }

    fun show() {
        dialogBinding = FragmentAddLabelDialogBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        dialogBinding.listener = this
        dialog = MaterialAlertDialogBuilder(context)
            .setTitle("Adicionar Rótulo")
            .setView(dialogBinding.root)
            .setPositiveButton("ADICIONAR", null)
            .setNegativeButton("CANCELAR", null)
        .create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                onAddClicked()
            }
        }
        dialog.show()
    }

    private fun onAddClicked() {
        val labelName = dialogBinding.addLabelText.text.toString().trim()
        if (!labelName.isNullOrEmpty() && selectedColorView != null) {
            val labelColor = getSelectedColor()
            val label = Label(
                name = labelName,
                color = labelColor
            )
            listener.addLabel(label)
            dialog.dismiss()
        }
    }

    override fun colorSelected(view: View) {
        if (selectedColorView == null) {
            selectedColorView = view
            val background = getSelectedDrawable(view.id)
            selectedColorView!!.background = background
        } else {
            var background = getDrawable(selectedColorView!!.id)
            selectedColorView!!.background = background

            selectedColorView = view
            background = getSelectedDrawable(view.id)
            selectedColorView!!.background = background
        }
    }

    private fun getSelectedDrawable(id: Int): Drawable? = when(id) {
        R.id.red_rect -> ContextCompat.getDrawable(context, R.drawable.rect_red_selected)
        R.id.green_rect -> ContextCompat.getDrawable(context, R.drawable.rect_green_selected)
        R.id.blue_rect -> ContextCompat.getDrawable(context, R.drawable.rect_blue_selected)
        R.id.yellow_rect -> ContextCompat.getDrawable(context, R.drawable.rect_yellow_selected)
        else -> ContextCompat.getDrawable(context, R.drawable.rect_light_blue_selected)
    }

    private fun getDrawable(id: Int): Drawable? = when(id) {
        R.id.red_rect -> ContextCompat.getDrawable(context, R.drawable.rect_red)
        R.id.green_rect -> ContextCompat.getDrawable(context, R.drawable.rect_green)
        R.id.blue_rect -> ContextCompat.getDrawable(context, R.drawable.rect_blue)
        R.id.yellow_rect -> ContextCompat.getDrawable(context, R.drawable.rect_yellow)
        else -> ContextCompat.getDrawable(context, R.drawable.rect_light_blue)
    }

    private fun getSelectedColor(): LabelColorEnum = when (selectedColorView?.id) {
        R.id.red_rect -> LabelColorEnum.RED
        R.id.green_rect -> LabelColorEnum.GREEN
        R.id.blue_rect -> LabelColorEnum.BLUE
        R.id.yellow_rect -> LabelColorEnum.YELLOW
        else -> LabelColorEnum.LIGHTBLUE
    }
}

interface ChooseColorListener {
    fun colorSelected(view: View)
}