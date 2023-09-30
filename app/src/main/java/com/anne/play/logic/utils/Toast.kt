package com.anne.play.logic.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 *
 * Author:AnneLo
 * Time:2023/9/22
 */
fun showToast(context: Context?, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun showToast(context: Context?, @StringRes msgId: Int) {
    Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show()
}

fun showLongToast(context: Context?, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun showLongToast(context: Context?, @StringRes msgId: Int) {
    Toast.makeText(context, msgId, Toast.LENGTH_LONG).show()
}