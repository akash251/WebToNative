package com.akash.webtonative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.akash.webtonative.domain.model.StickyFooterData
import com.akash.webtonative.presentation.NavGraphs
import com.akash.webtonative.presentation.bottom_bar_nav.BottomNavBar
import com.akash.webtonative.presentation.destinations.*
import com.akash.webtonative.presentation.screens.*
import com.akash.webtonative.ui.theme.WebToNativeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            LaunchedEffect(key1 = true) {
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }

            //Reading from json file
            val stickyFooterJsonData = applicationContext.resources.openRawResource(
                applicationContext.resources.getIdentifier(
                    "stickyfooter",
                    "raw",applicationContext.packageName
                )
            ).bufferedReader().use { it.readText() }

            val gson = Gson()
            val stickyFooterOutputJsonData = gson.fromJson(stickyFooterJsonData, StickyFooterData::class.java)

            val stickyData = stickyFooterOutputJsonData.stickyFooter.data[0]

            WebToNativeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(
                                navController = navController,
                                stickyData = stickyData
                            )
                        },
                        modifier = Modifier.systemBarsPadding()
                    ) {
                        DestinationsNavHost(
                            navController = navController,
                            navGraph = NavGraphs.root,
                            modifier = Modifier
                                .padding(it),
                        ){
                            composable(HomeScreenDestination){
                                HomeScreen(
                                    homePageUrl = stickyData.tabs[0].link,
                                    closeApp = {exitApp()}
                                )
                            }
                            composable(FeaturesScreenDestination){
                                FeaturesScreen(
                                    featuresPageUrl = stickyData.tabs[2].link
                                )
                            }
                            composable(PricingScreenDestination){
                                PricingScreen(
                                    pricingPageUrl = stickyData.tabs[4].link
                                )
                            }
                            composable(ShowCaseScreenDestination){
                                ShowCaseScreen(
                                   showCasePageUrl  = stickyData.tabs[1].link
                                )
                            }
                            composable(FaqsScreenDestination){
                                FaqsScreen(
                                    faqPageUrl = stickyData.tabs[3].link
                                )
                            }


                        }
                    }
                }
            }
        }
    }

    private fun exitApp(){
        this.finish()
    }
}

