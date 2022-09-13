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
import com.akash.webtonative.presentation.appDestination
import com.akash.webtonative.presentation.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun BottomNavBar(
    navController: NavController,
) {
    val currentDestination: com.akash.webtonative.presentation.destinations.Destination? =
        navController.currentBackStackEntryAsState().value?.appDestination()

    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        BottomBarDestination.values().forEach { destination ->

            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction, fun NavOptionsBuilder.() {
                        popUpTo(navController.graph[HomeScreenDestination.route].id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    })
                },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = "icons",
                        modifier = Modifier.size(20.dp)
                    )
                },

                label = {
                    Text(
                        text = destination.label,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxSize(),
                alwaysShowLabel = true
            )
        }
    }
}

