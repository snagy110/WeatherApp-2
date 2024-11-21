package com.telekommms.weatherapp.ui

sealed interface Screen {
    data object Home : Screen

    data object Permission : Screen

    data object EnterLocation : Screen

    data object Welcome : Screen

    data object Details : Screen
}