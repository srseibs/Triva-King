package com.sailinghawklabs.triviaking.domain.util

import androidx.core.text.HtmlCompat

fun expandHtmlCodes(stringWithHtml: String): String =
   HtmlCompat.fromHtml(stringWithHtml, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
