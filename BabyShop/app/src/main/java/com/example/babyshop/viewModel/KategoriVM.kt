package com.example.babyshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babyshop.model.Kategori
import com.example.babyshop.repository.KategoriRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KategoriVM: ViewModel() {
    val kategoriRepository = KategoriRepository()
    private val _kategori = MutableStateFlow<List<Kategori>>(emptyList())
    val kategori: StateFlow<List<Kategori>> get() = _kategori

    init {
        loadKategori()
    }

    fun loadKategori() {
        viewModelScope.launch {
            _kategori.value = kategoriRepository.getKategori()
        }
    }
}