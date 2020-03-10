package com.san4o.just4fun.authenticjobs.ui

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateTimeString(): String {
    return SimpleDateFormat("dd.MM.yyyy HH:mm").format(this)
}


fun View.setVisible(visible: Boolean?) {
    this.visibility = if (visible == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}