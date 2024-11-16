package com.example.babyshop.repository

import com.example.babyshop.model.Produk

class ProdukRepository {
    private val produk = listOf(
        Produk(1, "Baju Bayi", "https://i.pinimg.com/736x/35/a8/30/35a8301c52ff3dbfaafd0f5838281071.jpg", 100000.00, 5.0),
        Produk(2, "Kaos Kaki", "https://i.pinimg.com/736x/96/9c/3e/969c3eaed363ab4f9ce4790a620c9a8a.jpg", 200000.00, 5.0),
        Produk(3, "Shampo Bayi", "https://i.pinimg.com/474x/15/49/1e/15491e9b35c3c024343be56e0d08a9ff.jpg", 300000.00, 5.0),
        Produk(4, "Sabun Bayi", "https://i.pinimg.com/736x/a6/de/58/a6de581b797e1561dbbcad8f648bc0d8.jpg", 400000.00, 5.0),
        Produk(5, "Minyak Telon", "https://i.pinimg.com/474x/8a/50/4a/8a504af2349f8e143ee1d9cb4a0fc251.jpg", 500000.00, 5.0),
        Produk(6, "Botol Bayi", "https://i.pinimg.com/474x/02/6d/68/026d6814ca49f6520b7a78f8df92f625.jpg", 600000.00, 5.0),
        Produk(7, "Stroller Bayi", "https://i.pinimg.com/474x/43/67/53/4367536ffef5db8f20c5c464596c4d62.jpg", 200000.00, 5.0),
        Produk(8, "Bed Baby", "https://i.pinimg.com/474x/f4/d8/20/f4d820296ab9cc0b337674b26bfd783d.jpg", 900000.00, 5.0),
        Produk(9, "Gendongan Bayi", "https://i.pinimg.com/736x/f5/0f/d6/f50fd61a93c042ca36dc624fc0142226.jpg", 400000.00, 5.0),
        Produk(10, "Handuk Bayi", "https://i.pinimg.com/474x/d2/27/d3/d227d34fed256229c61eaa36fec9fad3.jpg", 200000.00, 5.0),
    )

    fun getProduk() : List<Produk> {
        return produk
    }
}