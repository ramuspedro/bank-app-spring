package com.example.bank.datasource.mock

import com.example.bank.datasource.BankDataSource
import com.example.bank.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {
    val banks = listOf(
        Bank("1234", 1.0, 1),
        Bank("2245", 1.0, 0),
        Bank("2266", 5.0, 14))
    override fun retrieveBanks(): Collection<Bank> = banks
}