package com.jadehelena.transactions.controller.form

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.domain.Transaction

class TransactionForm {

    private Long accountId
    private int operationType
    private Double amount

    Long getAccountId() {
        return accountId
    }

    int getOperationType() {
        return operationType
    }

    Double getAmount() {
        return amount
    }

    def convertToTransaction(accountService) {
        Account account = accountService.findByIdOrThrowBadRequestException(getAccountId())

        new Transaction(operationType, amount, account)
    }
}
