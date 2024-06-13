package com.brohit.show_phaze.common

import java.text.NumberFormat
import java.util.Currency

private val NumberFormatter by lazy {
    NumberFormat.getCurrencyInstance()
        .apply {
            this.maximumFractionDigits = 0
        }
}

fun Int.toCurrencyFormatOrThrow(
    country: String = "INR",
    maximumFractionDigits: Int = 0
): String {
    val c = NumberFormatter.currency
    val d = NumberFormatter.maximumFractionDigits
    NumberFormatter.currency = Currency.getInstance(country)
    NumberFormatter.maximumFractionDigits = maximumFractionDigits
    val format = NumberFormatter.format(this)
    NumberFormatter.currency = c
    NumberFormatter.maximumFractionDigits = d
    return format
}
