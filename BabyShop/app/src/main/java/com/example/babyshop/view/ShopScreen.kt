package com.example.babyshop.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.babyshop.R
import com.example.babyshop.component.BottomBarUI
import com.example.babyshop.component.TopBarUI
import com.example.babyshop.model.Produk
import com.example.babyshop.screen.Screen
import com.example.babyshop.viewModel.ProdukVM

@Composable
fun ShopScreen(navController: NavController, produkVM: ProdukVM) {
    val produks = produkVM.produk.collectAsState()
    val context = LocalContext.current

    Scaffold (
        topBar = {
            TopBarUI("Shop", false, navController)
        },
        bottomBar = {
            BottomBarUI(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (produks.value.isNotEmpty()){
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    columns = GridCells.Adaptive(minSize = 130.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(produks.value) { produk ->
                        ProdukCardItem(produk = produk) {
                            navController.navigate(Screen.DetailProduk.DetailProduk(produk.id)){
                                popUpTo(Screen.DetailProduk.route) {inclusive = true}
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProdukCardItem(produk: Produk, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation =8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = produk.gambar,
                    contentDescription = produk.nama,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier.size(30.dp).background(Color.Cyan, RoundedCornerShape(50.dp)).clip(CircleShape).align(Alignment.BottomEnd),
                    onClick = { onClick() },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Row (
                    modifier = Modifier.width(45.dp).background(Color.White, RoundedCornerShape(15.dp)).align(Alignment.TopStart),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${produk.star}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = produk.nama,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = "$${produk.harga}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShopScreen() {
    val navController = rememberNavController()
    val produkVM = ProdukVM()
    ShopScreen(navController, produkVM)
}
