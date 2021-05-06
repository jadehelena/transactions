package com.jadehelena.transactions.controller.dto

import com.jadehelena.transactions.domain.Transaction

class TransactionDto {
    private Long id
    private Long accountId
    private int operationType
    private Double amount

    TransactionDto(Transaction transaction) {
        this.id = transaction.getId()
        this.accountId = transaction.getAccount().getId()
        this.operationType = transaction.getOperationType()
        this.amount = transaction.getAmount()
    }

    Long getId() {
        return id
    }

    Long getAccountId() {
        return accountId
    }

    int getOperationType() {
        return operationType
    }

    Double getAmount() {
        return amount
    }
}
