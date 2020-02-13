package com.sprinter.mobws.widget

import android.view.View
import androidx.databinding.InverseMethod

object BindingConverters {

    @JvmStatic
    @InverseMethod("floatToString")
    fun floatToString(value: Float): String = value.toString()

    @JvmStatic
    @InverseMethod("intToString")
    fun stringToInt(value: String): Int = value.toIntOrNull() ?: 0

    @JvmStatic
    fun intToString(value: Int): String = value.toString()

    @JvmStatic
    @InverseMethod("boolToVisibility")
    fun boolToVisibility(value: Boolean): Int = if (value) View.VISIBLE else View.GONE
}
