package com.kelvinsugiarto.ministockwatch.ui.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kelvinsugiarto.ministockwatch.ui.R
import java.text.DecimalFormat


fun TextView.changeTextColor(changesPrice: Double, context: Context) {
    if (changesPrice < 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.red))
    } else if (changesPrice > 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.green))
    }
}

fun String.addPrefix(): String {
    val value = this.toDouble()
    return if (value < 0) {
        "-$value"
    } else if (value > 0) {
        "+$value"
    } else this
}

fun formatPriceChanges(value: Double): String {
    return DecimalFormat("##.##").format(value)
}

fun formatPrice(value: Double): String {
    return DecimalFormat("#,###").format(value)
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.showIf(condition : () -> Boolean) {
    if (condition.invoke()) setVisible()
    else setGone()
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showSlideUp() {
    setVisible()
    val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
    startAnimation(TranslateAnimation(0f, 0f, height.toFloat() + bottomMargin.toFloat(), 0f).apply {
        duration = 300
        fillAfter = true
    })
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat()
            ).apply {
                duration = 300
                fillAfter = true
            })
        setGone()
    }
}

fun View.showErrorSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.redDanger))
    snackbar.show()
}


fun View.showSuccessSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.green))
    snackbar.show()
}

fun Double?.orZero() = this ?: 0.0