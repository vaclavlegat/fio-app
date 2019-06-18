package cz.venjudev.fio.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.formatCurrency(): String {
    return Currency.getInstance(this).getSymbol(Locale.getDefault())
}

fun BigDecimal.formatAmountValue(): String {
    val amountFormatter = DecimalFormat("###,##0.00")
    val amountString = amountFormatter.format(this.toDouble())

    return if (amountString.endsWith(".00") || amountString.endsWith(",00")) amountString.substring(
        0,
        amountString.length - 3
    ) else amountString

}

fun Date.formatDate(): String {
    val format = SimpleDateFormat("dd. MM. yyyy", Locale.getDefault())
    return format.format(this)
}