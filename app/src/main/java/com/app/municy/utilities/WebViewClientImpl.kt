package com.app.municy.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.municy.interfaces.WebViewListener

open class WebViewClientImpl(activity: Activity?, private val webViewListener: WebViewListener) :
    WebViewClient() {

    private var activity: Activity? = null

    override fun shouldOverrideUrlLoading(
        webView: WebView,
        url: String
    ): Boolean {
        if (url.indexOf("www.vjhub.net") > -1) return false
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity!!.startActivity(intent)
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        webViewListener.onPageFinished(view, url)
    }

    init {
        this.activity = activity
    }
}