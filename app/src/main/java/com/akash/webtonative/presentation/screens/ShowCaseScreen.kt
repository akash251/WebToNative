package com.akash.webtonative.presentation.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ShowCaseScreen(showCasePageUrl:String) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(showCasePageUrl)
        }
    }, update = {
        it.loadUrl(showCasePageUrl)
    })
}