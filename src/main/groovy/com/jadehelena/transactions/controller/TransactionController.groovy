package com.jadehelena.transactions.controller

import com.jadehelena.transactions.controller.dto.TransactionDto
import com.jadehelena.transactions.controller.form.TransactionForm
import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.service.AccountService
import com.jadehelena.transactions.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping('transactions')
class TransactionController {

    @Autowired
    TransactionService transactionService

    @Autowired
    AccountService accountService

    @PostMapping
    ResponseEntity<Transaction> save(@RequestBody @Valid TransactionForm transactionForm) {
        Transaction transaction = transactionForm.convertToTransaction(accountService)
        Transaction persistedTransaction = transactionService.save(transaction)

        new ResponseEntity<>(new TransactionDto(persistedTransaction), HttpStatus.CREATED)
    }
}
