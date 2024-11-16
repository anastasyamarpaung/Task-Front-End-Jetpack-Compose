package com.example.babyshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babyshop.model.Produk
import com.example.babyshop.repository.ProdukRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProdukVM: ViewModel() {
    val produkRepository = ProdukRepository()
    private val _produk = MutableStateFlow<List<Produk>>(emptyList())
    val produk: StateFlow<List<Produk>>get() = _produk

    init {
        loadItem()
    }

    fun loadItem(){
        viewModelScope.launch {
            _produk.value = produkRepository.getProduk()
        }
    }
}