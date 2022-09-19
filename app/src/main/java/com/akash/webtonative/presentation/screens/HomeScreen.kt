package com.akash.webtonative.presentation.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.akash.webtonative.util.BackPressHandler
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    homePageUrl:String = "",
    closeApp:()->Unit
) {

    val context = LocalContext.current

    var backPressedCount by remember{ mutableStateOf(0) }




    val showToast = {
        Toast.makeText(
            context,
            "Press one more time to exit",
            Toast.LENGTH_SHORT
        ).show()
    }


    BackPressHandler(onBackPressed = {
        backPressedCount += it
        if (backPressedCount == 1){
            showToast()
        }
        if (backPressedCount >= 2){
            closeApp()
        }
    })

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(homePageUrl)
        }
    }, update = {
        it.loadUrl(homePageUrl)
    },
        modifier = Modifier.fillMaxSize()
    )
    
}