package com.anne.play.logic.utils

import android.os.Build
import android.text.Html

/**
 *
 * Author:AnneLo
 * Time:2023/9/27
 */
fun getHtmlText(text: String): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        text
    }
    return text
}