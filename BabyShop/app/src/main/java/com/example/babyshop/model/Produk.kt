package com.example.babyshop.model

data class Produk(
    val id: Int,
    val nama: String,
    val gambar: String,
    val harga: Double,
    var star: Double,
)