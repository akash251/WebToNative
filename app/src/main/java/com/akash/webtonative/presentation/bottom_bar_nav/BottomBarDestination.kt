package com.akash.webtonative.presentation.bottom_bar_nav

import com.akash.webtonative.R
import com.akash.webtonative.presentation.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: Int,
    val label: String,

) {
    Home(HomeScreenDestination, R.drawable.home_icon, "Home"),
    ShowCase(ShowCaseScreenDestination, R.drawable.showcase_icon, "Showcase"),
    Features(FeaturesScreenDestination, R.drawable.features_icon, "Features"),
    Faq(FaqsScreenDestination, R.drawable.faq_icon, "FAQ"),
    Pricing(PricingScreenDestination, R.drawable.pricing_icon, "Pricing"),

}