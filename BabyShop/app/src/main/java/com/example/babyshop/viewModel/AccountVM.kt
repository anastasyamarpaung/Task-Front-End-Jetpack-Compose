package com.example.babyshop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babyshop.model.Account
import com.example.babyshop.repository.AccountRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountVM: ViewModel() {
    val accountRepository = AccountRepository()
    private val _account = MutableStateFlow<List<Account>>(emptyList())
    val account: StateFlow<List<Account>> get() = _account

    init {
        loadAccount()
    }

    fun loadAccount(){
        viewModelScope.launch {
            _account.value = accountRepository.getAccount()
        }
    }
}