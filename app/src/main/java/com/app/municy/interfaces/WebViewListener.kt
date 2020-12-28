package com.app.municy.interfaces

import android.webkit.WebView

interface WebViewListener {

    fun onPageFinished(view: WebView?, url: String?)
}