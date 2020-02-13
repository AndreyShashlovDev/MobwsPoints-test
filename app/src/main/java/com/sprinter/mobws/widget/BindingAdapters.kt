package com.sprinter.mobws.widget

import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:onEditorEnterActionCustom")
    fun onEditorEnterActionCustom(textInputEditText: TextInputEditText, f: () -> Unit) {
        textInputEditText.setOnEditorActionListener { _, actionId, _ ->
            val imeAction = when (actionId) {
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_SEND,
                EditorInfo.IME_ACTION_GO -> true
                else -> false
            }
            if (imeAction)
                true.also { f() }
            else false
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:errorMessage", "app:minValue", "app:maxValue"])
    fun setNumberValidator(
        textInputLayout: TextInputLayout,
        errorMessage: String,
        min: Float,
        max: Float
    ) {
        textInputLayout.editText?.addTextChangedListener { text ->
            val value = text.toString().toFloatOrNull() ?: 0f
            val isValid = value in min..max

            textInputLayout.error = if (isValid) null else errorMessage
        }
    }
}
