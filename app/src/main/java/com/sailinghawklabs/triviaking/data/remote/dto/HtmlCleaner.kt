package com.sailinghawklabs.triviaking.data.remote.dto

import androidx.core.text.HtmlCompat

fun expandHtmlCodes(stringWithHtml: String): String =
   HtmlCompat.fromHtml(stringWithHtml, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
