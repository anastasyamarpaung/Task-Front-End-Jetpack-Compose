package com.example.babyshop.screen

sealed class Screen(val route:String) {
    object Home: Screen("home")
    object Shop: Screen("shop")
    object About: Screen("about")

    object DetailKategori : Screen("detailKategori/{id}") {
        fun DetailKategori(id: Int) = "detailKategori/$id"
    }

    object DetailProduk : Screen("detailProduk/{id}") {
        fun DetailProduk(id: Int) = "detailProduk/$id"
    }
}