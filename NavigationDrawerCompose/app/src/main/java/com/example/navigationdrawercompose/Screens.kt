package com.example.navigationdrawercompose

sealed class Screens(val screen:String) {
    data object Home:Screens("Home")
    data object Favorite:Screens("Favorite")
    data object Setting:Screens("Setting")
}