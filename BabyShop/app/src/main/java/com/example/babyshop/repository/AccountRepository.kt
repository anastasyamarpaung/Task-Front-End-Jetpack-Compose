package com.example.babyshop.repository

import com.example.babyshop.R
import com.example.babyshop.model.Account

class AccountRepository {
    private val account = listOf(
        Account(1, R.drawable.profile, "Anastasya Marpaung", "anastasya@gmail.com", "Institut Teknologi Del", "Teknologi Informasi")
    )

    fun getAccount() : List<Account> {
        return account
    }
}