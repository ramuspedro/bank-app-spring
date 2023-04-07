package com.example.bank.datasource.mock

import com.example.bank.datasource.BankDataSource
import com.example.bank.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf<Bank>(
        Bank("1234", 1.0, 11),
        Bank("2245", 1.0, 13),
        Bank("2266", 5.0, 14))
    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find the bank with account number = $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any {it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exist")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull() { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find the bank with account number = ${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        if (!banks.any {it.accountNumber == accountNumber}) {
            throw NoSuchElementException("Could not find the bank with account number = $accountNumber")
        }
        banks.removeIf {it.accountNumber == accountNumber}
    }
}