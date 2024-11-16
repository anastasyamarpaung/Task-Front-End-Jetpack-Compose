package com.example.babyshop.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.babyshop.R
import com.example.babyshop.component.BottomBarUI
import com.example.babyshop.component.TopBarUI
import com.example.babyshop.model.Kategori
import com.example.babyshop.model.Produk
import com.example.babyshop.screen.Screen
import com.example.babyshop.viewModel.KategoriVM
import com.example.babyshop.viewModel.ProdukVM

@Composable
fun HomeScreen(navController: NavController, kategoriVM: KategoriVM = viewModel(), produkVM: ProdukVM = viewModel()) {
    val kategori = kategoriVM.kategori.collectAsState()
    val produk = produkVM.produk.collectAsState()
    val context = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopBarUI("Home", false, navController)
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
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                item {
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        label = { Text("Search Products") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            Toast.makeText(context, "Searching for ${searchQuery.value}", Toast.LENGTH_SHORT).show()
                        })
                    )
                }

                item {
                    Text(
                        text = "Shop By Brand",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
                item {
                    LazyRow (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        items(kategori.value) { kategoris ->
                            KategoriSection(kategoris) {
                                navController.navigate(Screen.DetailKategori.DetailKategori(kategoris.id)){
                                    popUpTo(Screen.DetailKategori.route) {inclusive = true}
                                }
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        produk.value
                            .filter { it.nama.contains(searchQuery.value, ignoreCase = true) }
                            .forEach { produks ->
                                ProdukCard(produks) {
                                    navController.navigate(Screen.DetailProduk.DetailProduk(produks.id)){
                                        popUpTo(Screen.DetailProduk.route) {inclusive = true}
                                    }
                                }
                            }
                    }
                }
            }
        }
    }


}

@Composable
fun KategoriSection(kategori: Kategori, onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.LightGray, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = kategori.nama,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        }
    }
}

@Composable
fun ProdukCard(produk: Produk, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = colorResource(R.color.white))
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                AsyncImage(
                    model = produk.gambar,
                    contentDescription = produk.nama,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = produk.nama,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )

                Text(
                    text = "S$${produk.harga}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${produk.star}",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    val navController = rememberNavController()
    val kategoriVM = KategoriVM()
    HomeScreen(navController, kategoriVM)
}