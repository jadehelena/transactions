package com.jadehelena.transactions.service

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.enums.OperationTypeEnum
import com.jadehelena.transactions.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import javax.transaction.Transactional

@Service
class TransactionService {

    @Autowired
    TransactionRepository transactionRepository

    @Autowired
    AccountService accountService

    @Transactional
    Transaction save(Transaction transaction) {
        validatesTransactionParams(transaction)

        Transaction persistedTransaction = transactionRepository.save(transaction)
        accountService.updateAvailableCreditLimit(transaction.getAccount(), transaction.getAmount())

        persistedTransaction
    }

    def validatesTransactionParams(transaction) {
        OperationTypeEnum operationTypeEnum = OperationTypeEnum.getOperationTypeEnumIfExists(transaction)
        accountService.checkIfAvailableCreditLimitExist(transaction, operationTypeEnum)
        setNegativeAmountIfDebitOperation(operationTypeEnum, transaction)
    }

    def setNegativeAmountIfDebitOperation(operationTypeEnum, transaction) {
        if(operationTypeEnum in OperationTypeEnum.debitOperations()) {
            transaction.setAmount(transaction.amount * -1)
        }
    }
}
