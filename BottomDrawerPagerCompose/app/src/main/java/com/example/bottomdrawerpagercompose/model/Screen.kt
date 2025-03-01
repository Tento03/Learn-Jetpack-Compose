package com.example.bottomdrawerpagercompose.model

sealed class Screen(val screen:String) {
    data object Home:Screen("Home")
    data object Favorite:Screen("Favorite")
    data object Setting:Screen("Setting")
    data object Drawer:Screen("Drawer")
    data object Pager:Screen("Pager")
}