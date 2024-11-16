package com.example.babyshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.babyshop.screen.Screen
import com.example.babyshop.ui.theme.BabyShopTheme
import com.example.babyshop.view.AboutScreen
import com.example.babyshop.view.DetailKategori
import com.example.babyshop.view.DetailProduk
import com.example.babyshop.view.HomeScreen
import com.example.babyshop.view.ShopScreen
import com.example.babyshop.viewModel.KategoriVM
import com.example.babyshop.viewModel.ProdukVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            BabyShopTheme {
                MainNav()
            }
        }
    }

    @Composable
    fun MainNav() {
        val navController = rememberNavController()
        val destPage = Screen.Home.route

        NavHost(
            navController = navController,
            startDestination = destPage
        ) {
            composable(Screen.Home.route){
                val kategoriVM = KategoriVM()
                HomeScreen(navController, kategoriVM)
            }

            composable(Screen.Shop.route){
                val produkVM = ProdukVM()
                ShopScreen(navController, produkVM)
            }

            composable(Screen.About.route){
                AboutScreen(navController)
            }

            composable(
                route = Screen.DetailKategori.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val kategoriId = backStackEntry.arguments?.getInt("id")
                kategoriId?.let {
                    DetailKategori(navController = navController, id = it)
                }
            }

            composable(
                route = Screen.DetailProduk.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val produkId = backStackEntry.arguments?.getInt("id")
                produkId?.let {
                    DetailProduk(navController = navController, id = it)
                }
            }
        }
    }
}