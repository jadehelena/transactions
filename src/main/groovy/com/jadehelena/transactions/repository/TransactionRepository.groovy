package com.jadehelena.transactions.repository

import com.jadehelena.transactions.domain.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository extends JpaRepository<Transaction, Long> {

}