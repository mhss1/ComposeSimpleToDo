package com.example.composetodo.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatted() : String {
    val formatter = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault())
    val calender = Calendar.getInstance()
    calender.timeInMillis = this
    return formatter.format(calender.time)
}