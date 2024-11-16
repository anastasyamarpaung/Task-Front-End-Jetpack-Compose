package com.example.babyshop.repository

import com.example.babyshop.model.Kategori

class KategoriRepository {
    private val kategori = listOf(
        Kategori(1, "Pakaian Bayi", "Pakaian yang nyaman dan aman untuk bayi, dibuat dari bahan lembut seperti katun agar tidak menyebabkan iritasi pada kulit sensitif bayi"),
        Kategori(2, "Perlengkapan makan", "Menyediakan alat makan khusus bayi untuk memastikan kenyamanan dan keamanan saat makan."),
        Kategori(3, "Popok dan Perawatan", "Membantu menjaga kebersihan dan kesehatan kulit bayi"),
        Kategori(4, "Perlengkapan tidur", "Produk yang dirancang untuk memastikan tidur bayi nyaman dan aman"),
        Kategori(5, "Mainan dan Edukasi", "Mainan yang membantu stimulasi sensorik dan perkembangan kognitif bayi"),
        Kategori(6, "Perawatan dan Kebersihan", "Produk yang berisi untuk menjaga kesehatan dan kebersihan bayi sehari-hari"),
        Kategori(7, "Stroller dan Car Seat", "Produk yang mempermudah aktivitas luar ruangan bersama bayi"),
        Kategori(8, "Produk Kesehatan", "Produk yang membantu orang tua menjaga kesehatan bayi."),
        Kategori(9, "Gendongan dan Perjalanan", "Produk yang mendukung mobilitas dan kenyamanan saat bepergian bersama bayi"),
        Kategori(10, "Produk Kehamilan dan Menyusui", "Produk yang mendukung ibu dalam masa kehamilan dan menyusui"),
    )

    fun getKategori() : List<Kategori> {
        return kategori
    }
}