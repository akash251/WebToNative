package com.akash.webtonative.presentation.bottom_bar_nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.get
import com.akash.webtonative.R
import com.akash.webtonative.domain.model.Data
import com.akash.webtonative.presentation.appDestination
import com.akash.webtonative.presentation.destinations.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Destination
@Composable
fun BottomNavBar(
    navController: NavController,
    stickyData:Data
) {
    val currentDestination: com.akash.webtonative.presentation.destinations.Destination? =
        navController.currentBackStackEntryAsState().value?.appDestination()

    val destinations = listOf(
        HomeScreenDestination,
        ShowCaseScreenDestination,
        FeaturesScreenDestination,
        FaqsScreenDestination,
        PricingScreenDestination
    )

    val icons:List<Int> = listOf(
        R.drawable.home_icon,
        R.drawable.showcase_icon,
        R.drawable.features_icon,
        R.drawable.faq_icon,
        R.drawable.pricing_icon
    )

    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.height(stickyData.height.dp)
    ) {
        stickyData.tabs.forEachIndexed { index, tab->


            BottomNavigationItem(
                selected = currentDestination == destinations[index],
                onClick = {
                    navController.navigate(destinations[index].route, fun NavOptionsBuilder.() {
                        popUpTo(navController.graph[HomeScreenDestination.route].id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    })
                },
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = "icons",
                        modifier = Modifier.size(20.dp)
                    )
                },

                label = {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        fontSize = stickyData.fontSize.sp
                    )
                },
                modifier = Modifier.fillMaxSize(),
                alwaysShowLabel = true
            )
        }
    }
}

