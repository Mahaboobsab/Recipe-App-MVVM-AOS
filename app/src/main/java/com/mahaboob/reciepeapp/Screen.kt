package com.mahaboob.reciepeapp

class Screen {
    sealed class Screen(val route: String){
        object RecipeScreen: Screen("recipescreen")
        object DetailScreen: Screen("detailscreen")
    }
}