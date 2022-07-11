package com.rafagnin.tvshowcase.data.ext

import androidx.core.text.HtmlCompat

fun String?.parseHtml() = this?.let {
    HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()
}
