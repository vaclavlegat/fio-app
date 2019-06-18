package cz.venjudev.fio.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun View.tintedDrawable(@DrawableRes drawableId: Int, @ColorRes colorId: Int): Drawable {
    val tint: Int = ContextCompat.getColor(context, colorId)
    val drawable: Drawable = ContextCompat.getDrawable(context, drawableId)!!
    drawable.mutate()
    DrawableCompat.setTint(drawable, tint)
    return drawable
}