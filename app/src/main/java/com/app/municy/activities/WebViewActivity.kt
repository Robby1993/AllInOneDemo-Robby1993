package com.app.municy.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.app.municy.R
import com.app.municy.databinding.ActivityWebViewBinding
import com.app.municy.interfaces.WebViewListener
import com.app.municy.utilities.WebViewClientImpl

class WebViewActivity : AppCompatActivity(), WebViewListener {
    private lateinit var mBinding: ActivityWebViewBinding
    var title = ""
    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        mBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val intent = intent
        if (intent != null && intent.extras != null) {
            title = intent.extras!!.getString("title")!!
            url = intent.extras!!.getString("url")!!
        }
        // header.text = title
        val webSettings: WebSettings = mBinding.webView.settings
        webSettings.javaScriptEnabled = true
        mBinding.webView.webViewClient = WebViewClientImpl(this, this)
        mBinding.webView.loadUrl(url)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.mBinding.webView.canGoBack()) {
            this.mBinding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        mBinding.pBar.visibility = View.GONE
        mBinding.webView.visibility = View.VISIBLE

    }
}