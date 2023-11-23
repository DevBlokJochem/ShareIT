package com.plcoding.m3_bottomnavigation

sealed class Screen(val route: String) {

    object LoadingScreen: Screen(LOADINGTITLE)
    object RegisterScreen: Screen(REGISTERTITLE)
    object HomeScreen: Screen(HOMETITLE)
    object UploadScreen: Screen(UPLOADTITLE)
    object AccountScreen: Screen(ACCOUNTTITLE)

}
