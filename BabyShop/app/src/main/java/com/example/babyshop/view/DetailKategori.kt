package com.example.babyshop.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.babyshop.component.TopBarUI
import com.example.babyshop.viewModel.KategoriVM

@Composable
fun DetailKategori(navController: NavController, id: Int) {
    val kategoriVM = KategoriVM()
    val kategoriList = kategoriVM.kategori.collectAsState().value
    val kategori = kategoriList.find { it.id == id }

    Scaffold (
        topBar = {
            TopBarUI("Detail Kategori", true, navController)
        }
    ) { paddingValues ->
        if (kategori != null){
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.TopStart
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = kategori.nama,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Text(
                        text = kategori.deskripsi,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewDetail() {
    val navController = rememberNavController()
    val kategoriVM = KategoriVM()
    val kategori = kategoriVM.kategori.collectAsState().value
    if (kategori.isNotEmpty()){
        DetailKategori(navController, kategori[0].id)
    }
}